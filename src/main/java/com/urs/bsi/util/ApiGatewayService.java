package com.urs.bsi.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urs.bsi.model.AuthToken;
@Service
//@PropertySource(value="classpath:application.properties")
public class ApiGatewayService {
	
	public static final Logger logger=LoggerFactory.getLogger(ApiGatewayService.class);
	
	Map<String,AuthToken> authTokenLookUPMap = new HashMap<String,AuthToken>();
	
	@Value("${fg.client.key}")
	private String clientId;
	
	@Value("${fg.client.secret}")
	private String clientSecret;
	
	@Value("${eig.urs.base.url}")
	private String eigBaseUrl;
	
	@Value("${eig.urs.endpoint.url}")
	private String eigEndpointUrl;
	
	@Value("${eig.urs.client.key}")
	private String eigClientId;
	
	@Value("${eig.urs.client.secret}")
	private String eigClientSecret;
	
	@Value("${token.base.url}")
	private String tokenBaseUrl;
	
	@Value("${token.endpoint.url}")
	private String tokenEndpointUrl;
	
	public Map<String, AuthToken> getAuthTokenLookUPMap() {
		return authTokenLookUPMap;
	}

	public void setAuthTokenLookUPMap(Map<String, AuthToken> authTokenLookUPMap) {
		this.authTokenLookUPMap = authTokenLookUPMap;
	}

	public AuthToken createMacAccessToken() {
		
		logger.info("Start - create MacAccess Token ");
		try {
				String macAuthHeader = getMacAuthHeaderForTokenGeneration();
				return invokeTokenGenerationApi(clientId, macAuthHeader);

		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getMacAuthHeaderForTokenGeneration() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		
		Calendar calendar = Calendar.getInstance();
		String currentTimeInMilliseconds = Long.toString(calendar.getTimeInMillis());
		// String currentTimeInMilliseconds =String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli());
		StringBuilder nonce = new StringBuilder(currentTimeInMilliseconds + ":AMEX");
		StringBuilder tsn = new StringBuilder(currentTimeInMilliseconds + "\n");
		StringBuilder noncen = new StringBuilder(nonce + "\n");
		StringBuilder grantType = new StringBuilder("client_credentials\n");
		StringBuilder baseString = new StringBuilder(clientId + "\n" + tsn + noncen + grantType);
		String hash = getSignature(clientSecret, baseString.toString());
		StringBuilder macAuthHeader =  new StringBuilder("MAC id=\"" + clientId + "\",ts=\"" + currentTimeInMilliseconds + "\",nonce=\"" + nonce+ "\",mac=\"" + URLDecoder.decode(hash, "UTF-8") + "\"");
		logger.info("macAuthHeader is =" + macAuthHeader);
		return macAuthHeader.toString();
	}
	
	public String getMacAuthHeaderForEIG(String json) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		String currentTimeInMilliseconds = Long.toString(Calendar.getInstance().getTimeInMillis());
		StringBuilder nonce = new StringBuilder(currentTimeInMilliseconds + ":AMEX");
		StringBuilder tsn = new StringBuilder(currentTimeInMilliseconds + "\n");
		StringBuilder noncen = new StringBuilder(nonce + "\n");
		StringBuilder resPath = new StringBuilder(eigEndpointUrl + "\n");
		StringBuilder host = new StringBuilder(eigBaseUrl).append("\n");
		String hostValue = host.toString().replace("https://", "");
		
		String bodyhash = getSignature(eigClientSecret,json);
		
		String baseString = tsn.toString() + noncen.toString() + "POST\n" + resPath + hostValue + "443\n"+bodyhash + "\n";
		String hash = getSignature(eigClientSecret, baseString);
		
		StringBuilder macAuthHeader = new StringBuilder("MAC id=\"" + eigClientId + "\",ts=\""+ currentTimeInMilliseconds + "\",nonce=\"" + nonce+ "\",bodyhash=\""+bodyhash+"\",mac=\"" + hash +"\"");
		logger.info("Reject macAuthHeader="+macAuthHeader);
		
		return macAuthHeader.toString();
	}

	public String getSignature(String secret, String message) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac sha256_HMAC;
		sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
		return hash;
		
	}

	private AuthToken invokeTokenGenerationApi(String clientId, String macAuthHeader) throws Exception {
		
		String url = tokenBaseUrl + tokenEndpointUrl;
		String payload = "grant_type=client_credentials&scope=&";

		HttpPost post = setHttpPostHeadersForTokenApi(clientId, macAuthHeader, url, payload);
		logger.info("calling Token generation API: "+post.getURI());
		AuthToken authToken = callPostApi(post, AuthToken.class);
		
		if(null!=authToken)
			authToken.setExpires_date(getDateTime(authToken.getExpires_in()));
		else{
			authToken=retryAPI(post, AuthToken.class,10,"tokenApi");
			authToken.setExpires_date(getDateTime(authToken.getExpires_in()));
		}
		
		logger.info("End - MacAccess Token ");
		return authToken;
	}
	
	private HttpPost setHttpPostHeadersForTokenApi(String clientId, String macAuthHeader, String url, String payload) {
		HttpPost post = new HttpPost(url);
		post.setHeader("X-AMEX-API-KEY", clientId);
		post.setHeader("Authentication", macAuthHeader);
		post.setHeader("client_app_id", "URS");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setEntity(new StringEntity(payload, "UTF-8"));
		return post;
	}

	private Date getDateTime(String expires_in) {
		
		Calendar cal=Calendar.getInstance();
		Date date=new Date();
		cal.setTimeInMillis(date.getTime());
		cal.add(Calendar.MILLISECOND, Integer.valueOf(expires_in));
		return new Date(cal.getTime().getTime());
	}


	public <T> T callPostApi(HttpPost post, Class<T> clazz)
			throws IOException, ClientProtocolException, JsonParseException, JsonMappingException {
		logger.info("Processing request:"+post.getURI());
		T responseModel = null;
		CloseableHttpClient client = HttpClientBuilder.create().build();
		CloseableHttpResponse response = client.execute(post);
		ObjectMapper map = new ObjectMapper();
		if(response.getStatusLine().getStatusCode()!=200) {
			logger.error("API failed. Error code:"+response.getStatusLine().getStatusCode()+" Message:"+response.getStatusLine());
			responseModel = map.readValue(response.getEntity().getContent(), clazz);
		}
		else{
			logger.info("API invoked successfully. ResponseCode:"+response.getStatusLine().getStatusCode()+" Message:"+response.getStatusLine());
			responseModel = map.readValue(response.getEntity().getContent(), clazz);
		}
		post.releaseConnection();
		
		return responseModel;
	}
	
	public <T> T retryAPI(HttpPost post, Class<T> clazz, int retryCount,String apiflag)
			throws IOException, ClientProtocolException, JsonParseException, JsonMappingException, InvalidKeyException, NoSuchAlgorithmException {
		int count=0;
		try {
			 do{
				 	logger.error("Retrying...");
					Thread.sleep(10);
					if(apiflag.equalsIgnoreCase("tokenApi"))
						post.setHeader("Authentication",getMacAuthHeaderForTokenGeneration());
					T responseModel=callPostApi(post, clazz);
					if(null!=responseModel) return responseModel;
					logger.error("Retry failed. Count:"+count);
					count++;
				}while (count < retryCount); 
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.error("error - ",e);
		}
		return null;
	}
}
