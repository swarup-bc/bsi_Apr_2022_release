package com.urs.bsi.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urs.bsi.model.AuthToken;
import com.urs.bsi.model.SpreadingAccountData;
import com.urs.bsi.model.SpreadingMasterData;
import com.urs.bsi.model.SpreadingRequestData;
import com.urs.bsi.model.SpreadingSummaryData;
import com.urs.bsi.model.SpreadingTransactionData;
import com.urs.bsi.model.TplHistory;
import com.urs.bsi.model.URSIoJsonDetails;
import com.urs.bsi.model.vo.AccountDataVo;
import com.urs.bsi.model.vo.CLToURSResponseVo;
import com.urs.bsi.model.vo.CustomerDataVo;
import com.urs.bsi.model.vo.DocumentRejectionVo;
import com.urs.bsi.model.vo.FGToURSResponseVo;
import com.urs.bsi.model.vo.MonthlyDataVo;
import com.urs.bsi.model.vo.RejectedReason;
import com.urs.bsi.model.vo.RequestedAccount;
import com.urs.bsi.model.vo.TransactionDataVo;
import com.urs.bsi.model.vo.URSToFGRequestVo;
import com.urs.bsi.repository.SpreadingAccountDataRepository;
import com.urs.bsi.repository.SpreadingMasterDataRepository;
import com.urs.bsi.repository.SpreadingRequestDataRepository;
import com.urs.bsi.repository.SpreadingSummaryDataRepository;
import com.urs.bsi.repository.SpreadingTransactionDataRepository;
import com.urs.bsi.repository.TplHistoryRepository;
import com.urs.bsi.repository.URSIoJsonDetailsRepository;
import com.urs.bsi.util.ApiGatewayService;
import com.urs.bsi.util.BsiFileUtility;
import com.urs.bsi.util.SendEmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
//@PropertySource(value="classpath:application.properties")
public class URSConsumptionAPIServiceImpl implements URSConsumptionAPIService {
	
	private static final String NA = "NA";

	private static final String EMPTY_STRING = "";

	@Autowired
	SpreadingMasterDataRepository spreadingMasterDataRepository;
	
	@Autowired
	SpreadingRequestDataRepository spreadingRequestDataRepository;
	
	@Autowired
	SpreadingAccountDataRepository spreadingAccountDataRepository;
	
	@Autowired
	SpreadingSummaryDataRepository spreadingSummaryDataRepository;
	
	@Autowired
	SpreadingTransactionDataRepository spreadingTransactionDataRepository;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private URSIoJsonDetailsRepository ursIoJsonDetailsRepository;
	
	@Autowired
	private TplHistoryRepository tplHistoryRepository;
	
	@Autowired
	ApiGatewayService apiGatewayService;
	
	public static final Logger logger = LoggerFactory.getLogger(URSConsumptionAPIServiceImpl.class);
	
	Map<String,AuthToken> authTokenLookUPMap = new HashMap<String,AuthToken>();
	
	@Value("${eig.urs.base.url}")
	private String eigBaseUrl;
	
	@Value("${eig.urs.endpoint.url}")
	private String eigEndpointUrl;
	
	@Value("${eig.urs.client.key}")
	private String eigClientId;
	
	@Value("${eig.urs.client.secret}")
	private String eigClientSecret;
	
	@Value("${fg.client.key}")
	private String clientId;
	
	@Value("${fg.client.secret}")
	private String clientSecret;
	
	@Value("${fg.base.url}")
	private String fgBaseUrl;
	
	@Value("${fg.endpoint.url}")
	private String fgEndpointUrl;
	
	@Value("${cl.client.id}")
	private String clClientId;
	
	@Value("${cl.client.secret}")
	private String clClientSecret;
	
	@Value("${cl.token.url}")
	private String clTokenUrl;
	
	@Value("${cl.api.url}")
	private String clAPIUrl;
	
	@Value("${cl.client.username}")
	private String clClientUserName;
	
	@Value("${cl.client.password}")
	private String clClientPassword;
	
	@Value("${environment}")
	private String environment;
	
	 @Autowired
	 private RestTemplate restTemplate;

	 public String postRejectionJson(String json) throws RestClientException, URISyntaxException {
		 String crId = null;
		 try {
			 
//			 json = getDummyJSON();
				
		     logger.info("reject json="+json);
		     logger.info("Rejection URL="+eigBaseUrl+eigEndpointUrl);
		     crId = BsiFileUtility.getUuidBatchId();
		     logger.info("correlation_id="+crId);
		     
		     HttpHeaders hh = new HttpHeaders();
		     hh.setContentType(MediaType.APPLICATION_JSON);
		     hh.set("x-amex-api-key",eigClientId);
		     hh.set("request_timestamp",BsiFileUtility.getCurrentMSTTimeMMDDYYYYHHMMSS());
		     hh.set("correlation_id",crId);
		     hh.set("Authorization",apiGatewayService.getMacAuthHeaderForEIG(json));
		     HttpEntity<?> httpEntity = new HttpEntity<>(json, hh);

		     ResponseEntity<String> resp = restTemplate.exchange( new URI(eigBaseUrl+eigEndpointUrl), HttpMethod.POST, httpEntity, String.class);

		     saveEIGJsonToDB(crId,resp.getBody(),resp.getBody(),json);
		     return "200~Success";
		 } catch (HttpClientErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.error("error - ",e);
			logger.info(e.getResponseBodyAsString());
			saveEIGJsonToDB(crId,e.getResponseBodyAsString(),String.valueOf(e.getStatusCode()),json);
			return String.valueOf(e.getRawStatusCode()+"~"+e.getStatusText());
		 }catch(HttpServerErrorException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.error("error - ",e);
			logger.info(e.getResponseBodyAsString());
			saveEIGJsonToDB(crId,e.getResponseBodyAsString(),String.valueOf(e.getStatusCode()),json);
			return String.valueOf(e.getRawStatusCode()+"~"+e.getStatusText());
		 }catch(Exception e) {
			 e.printStackTrace();
			 logger.error(e.getMessage());
			 logger.error("error - ",e);
			 saveEIGJsonToDB(crId,"Error","500",json);
		    		try {
		    			StringWriter sw = new StringWriter();
		                e.printStackTrace(new PrintWriter(sw));
		                String exceptionAsString = sw.toString();
						SendEmail.sendmail(environment+" : EIG Reject API 1000 Error - "+crId, 
							"AxpSupport.BankSpreading@blucognition.com",exceptionAsString);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			 return "500~Error";
		}
	 }

	private String getDummyJSON() throws JsonProcessingException {
		String json;
		DocumentRejectionVo drVo = new DocumentRejectionVo();
			drVo.setEvent("CASE_REJECTED");
			drVo.setJourney("REACTIVE-LINE-INCREASE");
			drVo.setJourneyTrackingNumber("2018327CG01183USD");
			drVo.setIsSpreadComplete(false);
			RejectedReason rr = new RejectedReason();
			rr.setCode("NLE");
			rr.setDocumentResourceIdentifier("1234");
			rr.setText("The name on the documents doesnot match the name on your account");
			drVo.setRejectReason(Arrays.asList(rr));
			drVo.setStatus("REJECTED");
			ObjectMapper om=new ObjectMapper();	
			json = om.writeValueAsString(drVo);
		return json;
	}
	 
	 private void saveEIGJsonToDB(String corrId, String responseJson,String responseCode,String reqJson) {
		 try {
			 	logger.info("Reject API response 200 - "+corrId);
				URSIoJsonDetails usrIoJsonDetails=new URSIoJsonDetails();
				usrIoJsonDetails.setResponseJson(responseJson);
				usrIoJsonDetails.setCorrelationId(corrId);
				usrIoJsonDetails.setResponseCode(responseCode);
				usrIoJsonDetails.setFromEntity("URS");
				usrIoJsonDetails.setToEntity("EIG");
				usrIoJsonDetails.setRequestJson(reqJson);
				usrIoJsonDetails.setCreatedDate(BsiFileUtility.getCurrentTimeMMDDYYYYHHMMSS());
				logger.info("Inserting data in JSON tbl");
				ursIoJsonDetailsRepository.save(usrIoJsonDetails);
				logger.info("Data inserted successfully JSON tbl");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
			}
	 }
			
	public FGToURSResponseVo callFgPostAPI(String postEntity,String useCaseId,String marketCode) throws UnsupportedEncodingException {
		AuthToken accessToken = null;
		int count=0;
		Exception ex = null;
		String corrlnId = null;
		HttpPost post = null;
		do{
		if(isTokenAvaliable())
			accessToken=authTokenLookUPMap.get(clientId+":"+clientSecret);
		else{
			accessToken = apiGatewayService.createMacAccessToken();
			authTokenLookUPMap.put(clientId+":"+clientSecret,accessToken);
		}
		
		if (null != accessToken) {
			
			logger.info("Access Token is ="+accessToken.getAccess_token());
			logger.info("Start - calling FgPostAPI");
			try {
				post = getMacAuthHeaderForFgApi(apiGatewayService, accessToken,useCaseId,marketCode);
				post.setEntity(new StringEntity(postEntity.toString(), "UTF-8"));
				Header[] headers = post.getAllHeaders();
				for (Header header : headers) {
					logger.info(header.getName() + ": " + header.getValue());
					if("correlation_id".equalsIgnoreCase(header.getName()))
							corrlnId = header.getValue();
				}
				FGToURSResponseVo fgResponseModel = apiGatewayService.callPostApi(post, FGToURSResponseVo.class);
				if(null!=fgResponseModel)
					return fgResponseModel;
					//apiGatewayService.retryAPI(post, FGToURSResponseVo.class,3,"fgPostAPI");
				logger.info("End - FgPostAPI");
				
			} 
			catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				logger.error("error - ",e);
				ex = e;
			}
			count++;
		}else 
			logger.info("Access Token is null");
		
		}while(count< 3);
		
		if(null!=ex) {
    		try {
    			StringWriter sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
				SendEmail.sendmail(environment+" : TPDG Downstream MAIN API 1000 Error - "+corrlnId, 
					"AxpSupport.BankSpreading@blucognition.com", exceptionAsString);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				logger.error("error - ",e1);
			}
    	}
		
		FGToURSResponseVo fgResponseModel = new FGToURSResponseVo<>();
		fgResponseModel.setResponse_id(corrlnId);
		fgResponseModel.setResponse_code("500");
		return fgResponseModel;
	}

	private HttpPost getMacAuthHeaderForFgApi(ApiGatewayService apiGatewayService, AuthToken accessToken,String useCaseId,String marketCode)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

		String currentTimeInMilliseconds = Long.toString(Calendar.getInstance().getTimeInMillis());
		// String currentTimeInMilliseconds =String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli());
		StringBuilder nonce = new StringBuilder(currentTimeInMilliseconds + ":AMEX");
		StringBuilder tsn = new StringBuilder(currentTimeInMilliseconds + "\n");
		StringBuilder noncen = new StringBuilder(nonce + "\n");
		StringBuilder resPath = new StringBuilder(URLEncoder.encode(fgEndpointUrl,StandardCharsets.UTF_8.toString()) + "\n");
		StringBuilder host = new StringBuilder(fgBaseUrl).append("\n");
		StringBuilder baseString = new StringBuilder(tsn.toString() + noncen.toString() + "POST\n" + resPath + host.toString().replace("https://", "") + "443\n" + "\n");
		String hash = apiGatewayService.getSignature(accessToken.getMac_key(), baseString.toString());
		String signature = URLDecoder.decode(hash, "UTF-8").replace("\r\n", EMPTY_STRING);
		StringBuilder macAuthHeader = new StringBuilder("MAC id=\"" + accessToken.getAccess_token() + "\",ts=\""+ currentTimeInMilliseconds + "\",nonce=\"" + nonce + "\",mac=\"" + signature + "\"");
		StringBuilder url = new StringBuilder(fgBaseUrl + fgEndpointUrl);
		
		HttpPost post = new HttpPost(url.toString());
		post.setHeader("Authorization", macAuthHeader.toString());
		post.setHeader("X-AMEX-API-KEY", clientId);
		post.setHeader("client_app_id", "URS");
		post.setHeader("Content-Type", "application/json");
		post.setHeader("country_code",marketCode);
		post.setHeader("currency_code","USD");
		post.setHeader("client_channel_code","Web");
		post.setHeader("client_platform_code","clientplatform");
		post.setHeader("request_timestamp",BsiFileUtility.getCurrentMSTTimeMMDDYYYYHHMMSS());
		post.setHeader("usecase_id",useCaseId);
		post.setHeader("correlation_id",BsiFileUtility.getUuidBatchId());
		
		return post;
	}
	
	private boolean isTokenAvaliable() {
		return null!=authTokenLookUPMap && authTokenLookUPMap.containsKey(clientId+":"+clientSecret) && null!=authTokenLookUPMap.get(clientId+":"+clientSecret) && null!=authTokenLookUPMap.get(clientId+":"+clientSecret).getExpires_date() && authTokenLookUPMap.get(clientId+":"+clientSecret).getExpires_date().after(new Date());
	}

	
	public URSToFGRequestVo prepareURSToFgJson(TplHistory th){
		SpreadingMasterData spreadingMasterData=spreadingMasterDataRepository.findByHistoryId(th.getId());
		if(null==spreadingMasterData) return null;
		URSToFGRequestVo customerDataVo=prepareCustomerDataVo(spreadingMasterData,th.getRefSrdId());
		return customerDataVo;
	}
	
	private void saveFgJsonToTbl(FGToURSResponseVo fGToURSResponseVo, String customerDataVo) {
		try {
			ObjectMapper om=new ObjectMapper();
			URSIoJsonDetails usrIoJsonDetails=new URSIoJsonDetails();
			if(null!=fGToURSResponseVo) {
				usrIoJsonDetails.setResponseJson(om.writeValueAsString(fGToURSResponseVo));
				usrIoJsonDetails.setCorrelationId(fGToURSResponseVo.getResponse_id());
				usrIoJsonDetails.setResponseCode(fGToURSResponseVo.getResponse_code());
			}else {
				usrIoJsonDetails.setResponseCode("500");
			}
			usrIoJsonDetails.setFromEntity("URS");
			usrIoJsonDetails.setToEntity("TPDG");
			usrIoJsonDetails.setRequestJson(customerDataVo);
			usrIoJsonDetails.setCreatedDate(BsiFileUtility.getCurrentTimeMMDDYYYYHHMMSS());
			logger.info("Inserting data in JSON tbl");
			ursIoJsonDetailsRepository.save(usrIoJsonDetails);
			logger.info("Data inserted successfully JSON tbl");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.error("error - ",e);
		}
	}

	@Override
	public FGToURSResponseVo consumeFGApi(String caseId) throws JsonProcessingException, UnsupportedEncodingException{
		TplHistory th = tplHistoryRepository.findById(Long.parseLong(caseId)).get();
		URSToFGRequestVo customerDataVo=prepareURSToFgJson(th);
		if(null==customerDataVo) {
			logger.info("customerDataVo is null for caseId -"+caseId);
			return null;
		}
		ObjectMapper om=new ObjectMapper();	
		String postEntity = om.writeValueAsString(customerDataVo);
		
		logger.info("JSON to TPDG for caseId -"+caseId+" is , "+postEntity);
		String useCaseId = customerDataVo.getCustomer_data().getUsecaseId();
		Optional<SpreadingRequestData> osrd = spreadingRequestDataRepository.findById(th.getRefSrdId());
		SpreadingRequestData srd = osrd.get();
		if(NA.equalsIgnoreCase(srd.getUsecase_id())){
			try {
				JSONObject json = new JSONObject(postEntity);
				json.getJSONObject("customer_data").remove("requested_account");
				postEntity = json.toString();
				logger.info("Updated JSON for NA case to TPDG for caseId -"+caseId+" is , "+postEntity);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
				logger.error("error - ",e);
				logger.info("Error creating JSON for NA case to TPDG for caseId -"+caseId);
			}
		}
		FGToURSResponseVo fGToURSResponseVo=callFgPostAPI(postEntity.toString(),useCaseId,srd.getMarket_code());
		
		saveFgJsonToTbl(fGToURSResponseVo, postEntity);
		
		return fGToURSResponseVo;
	}

	private URSToFGRequestVo prepareCustomerDataVo(SpreadingMasterData spreadingMasterData,long srdId) {
		
		URSToFGRequestVo ursToFGRequestVo=new URSToFGRequestVo();
		CustomerDataVo customerDataVo=new CustomerDataVo();
		
		RequestedAccount requestedAccount=new RequestedAccount();
		List<RequestedAccount> requestedAccountList =new ArrayList<>();
		
		
		customerDataVo.setBusiness_id(null!=spreadingMasterData.getBusinessId()?spreadingMasterData.getBusinessId():EMPTY_STRING);
		customerDataVo.setCase_id(null!=spreadingMasterData.getBusinessId()?spreadingMasterData.getBusinessId():EMPTY_STRING);
		customerDataVo.setRequest_limit(null!=spreadingMasterData.getRequestedLimit()?spreadingMasterData.getRequestedLimit():EMPTY_STRING);
		customerDataVo.setSpread_date(spreadingMasterData.getSpreadingDoneOn().replaceAll("-", "/"));
		
		customerDataVo.setContainer_type(null!=spreadingMasterData.getContainerType()?spreadingMasterData.getContainerType():EMPTY_STRING);
		customerDataVo.setSource_of_origin(null!=spreadingMasterData.getDataSource()?spreadingMasterData.getDataSource():EMPTY_STRING);
		
		customerDataVo.setDoing_business_as_name(EMPTY_STRING);
		customerDataVo.setConsumer_name(EMPTY_STRING);
		customerDataVo.setRe_spread_indicator("false");
		
		Optional<SpreadingRequestData> osrd = spreadingRequestDataRepository.findById(srdId);
		SpreadingRequestData srd = osrd.get();
		customerDataVo.setProcess_control_number(srd.getProcess_control_number());
		customerDataVo.setCompany_name(srd.getCompany_name());
		
		if("RLI".equalsIgnoreCase(srd.getUsecase_id()) && "GCP".equalsIgnoreCase(srd.getRequesting_bu())) {
			requestedAccount.setUnique_id_type("client_org_id");
			requestedAccount.setUnique_id(srd.getCorporate_id());
			customerDataVo.setAccount_business_unit_code("GCP");
		}else if("PFG".equalsIgnoreCase(srd.getUsecase_id()) && "GCP".equalsIgnoreCase(srd.getRequesting_bu())) {
			requestedAccount.setUnique_id_type("client_org_id");
			requestedAccount.setUnique_id(srd.getCorporate_id());
			customerDataVo.setAccount_business_unit_code("GCP");
		}else if(NA.equalsIgnoreCase(srd.getUsecase_id()) && "GCP".equalsIgnoreCase(srd.getRequesting_bu())) {
			customerDataVo.setAccount_business_unit_code("GCP");
			customerDataVo.setCompany_name(srd.getCompany_name());
		}else if(NA.equalsIgnoreCase(srd.getUsecase_id()) && "OPEN".equalsIgnoreCase(srd.getRequesting_bu())) {
			customerDataVo.setAccount_business_unit_code("OPEN");
			customerDataVo.setCompany_name(srd.getCompany_name());
		}else {
			customerDataVo.setAccount_business_unit_code(srd.getRequesting_bu());
		}
		customerDataVo.setUsecaseId(srd.getUsecase_id());

//		requestedAccount.setUnique_id_type("client_org_id");
//		customerDataVo.setAccount_business_unit_code("GCP");
//		requestedAccount.setUnique_id("036108418");
//		customerDataVo.setUsecaseId("RLI");

		requestedAccountList.add(requestedAccount);
		customerDataVo.setRequested_account(requestedAccountList);
		customerDataVo.setAccount_data(getAccountVo(spreadingAccountDataRepository.findByHistoryId(spreadingMasterData.getHistoryId())));
		//customerDataVo.setCompany_name(customerDataVo.getAccount_data().get(0).getAccount_holder_name()); // Setting company name same as first account holder name
		ursToFGRequestVo.setCustomer_data(customerDataVo);
		return ursToFGRequestVo;
	}

	private List<AccountDataVo> getAccountVo(List<SpreadingAccountData> spreadingAccountDataList) {
		
		List<AccountDataVo> accountDataVoList=new ArrayList<AccountDataVo>(); 
		for (SpreadingAccountData spreadingAccountData : spreadingAccountDataList) {
		
			AccountDataVo accountDataVo=new AccountDataVo();
			accountDataVo.setCustomer_name1(EMPTY_STRING);
			accountDataVo.setCustomer_name2(EMPTY_STRING);
			accountDataVo.setCustomer_name3(EMPTY_STRING);
    		accountDataVo.setVendor_credit_share1(EMPTY_STRING);
			accountDataVo.setVendor_credit_share2(EMPTY_STRING);
			accountDataVo.setVendor_credit_share3(EMPTY_STRING);
			accountDataVo.setBank_name(null!=spreadingAccountData.getBankName()?spreadingAccountData.getBankName():EMPTY_STRING);
			accountDataVo.setBank_account_number(null!=spreadingAccountData.getAccountNumber()?BsiFileUtility.decrypt(spreadingAccountData.getAccountNumber()):EMPTY_STRING);
			accountDataVo.setBank_routing_transit_number(null!=spreadingAccountData.getRoutingNo()?spreadingAccountData.getRoutingNo():EMPTY_STRING);
			accountDataVo.setAccount_type(null!=spreadingAccountData.getAccountType()?spreadingAccountData.getAccountType():EMPTY_STRING);
			accountDataVo.setAccount_holder_name(null!=spreadingAccountData.getAccountHolderName()?BsiFileUtility.decrypt(spreadingAccountData.getAccountHolderName()):EMPTY_STRING);
			accountDataVo.setSecondary_account_holder_name(null!=spreadingAccountData.getSecondaryAccountHolderName()?spreadingAccountData.getSecondaryAccountHolderName():EMPTY_STRING);
			accountDataVo.setAs_of_date(null!=spreadingAccountData.getAsOfDate()?spreadingAccountData.getAsOfDate():EMPTY_STRING);
			accountDataVo.setCust1share(EMPTY_STRING);
			accountDataVo.setCust2share(EMPTY_STRING);
			accountDataVo.setCust3share(EMPTY_STRING);
			accountDataVo.setVendor_name1(EMPTY_STRING);
			accountDataVo.setVendor_name2(EMPTY_STRING);
			accountDataVo.setVendor_name3(EMPTY_STRING);
			accountDataVo.setAddress_line1(null!=spreadingAccountData.getAddressLine1()?spreadingAccountData.getAddressLine1():EMPTY_STRING);
			accountDataVo.setAddress_line2(null!=spreadingAccountData.getAddressLine2()?spreadingAccountData.getAddressLine2():EMPTY_STRING);
			accountDataVo.setAddress_line3(null!=spreadingAccountData.getAddressLine3()?spreadingAccountData.getAddressLine3():EMPTY_STRING);
			accountDataVo.setZip_code(null!=spreadingAccountData.getZip()?spreadingAccountData.getZip():EMPTY_STRING);
			accountDataVo.setCity_name(null!=spreadingAccountData.getCity()?spreadingAccountData.getCity():EMPTY_STRING);
			accountDataVo.setState_code(null!=spreadingAccountData.getState()?spreadingAccountData.getState():EMPTY_STRING);
			accountDataVo.setCountry_code(null!=spreadingAccountData.getCountryCode()?spreadingAccountData.getCountryCode():EMPTY_STRING);
			accountDataVo.setCountry_name(null!=spreadingAccountData.getCountry()?spreadingAccountData.getCountry():EMPTY_STRING);
			accountDataVo.setMonthly_data(getMonthlyDataVo(spreadingSummaryDataRepository.findBySpreadingAdataId(spreadingAccountData.getId())));
			accountDataVo.setAccount_ownership(spreadingAccountData.getAccountOwnership());
			accountDataVoList.add(accountDataVo);
		}
		return accountDataVoList; 
	}

	private List<MonthlyDataVo> getMonthlyDataVo(List<SpreadingSummaryData> spreadingSummaryDataList) {
		
		List<MonthlyDataVo> monthlyDataVoList=new ArrayList<>();
		for (SpreadingSummaryData spreadingSummaryData : spreadingSummaryDataList) {
			
		MonthlyDataVo monthlyDataVo=new MonthlyDataVo();
		monthlyDataVo.setEnding_balance_amount(null!=spreadingSummaryData.getClosingBalance()?String.valueOf(spreadingSummaryData.getClosingBalance()):EMPTY_STRING);
		monthlyDataVo.setUtilization_count(EMPTY_STRING);
		monthlyDataVo.setPayment_amount(EMPTY_STRING);
		monthlyDataVo.setCheck_return_count(null!=spreadingSummaryData.getTotalNumberOfCheckReturns()?String.valueOf(spreadingSummaryData.getTotalNumberOfCheckReturns()):EMPTY_STRING);
		monthlyDataVo.setCurrency_unit(null!=spreadingSummaryData.getUnitOfCurrency()?spreadingSummaryData.getUnitOfCurrency():EMPTY_STRING);
		monthlyDataVo.setPayment_count(EMPTY_STRING);
		monthlyDataVo.setEnding_statement_balance_amount(null!=spreadingSummaryData.getClosingBalance()?String.valueOf(spreadingSummaryData.getClosingBalance()):EMPTY_STRING);
		monthlyDataVo.setStatement_start_date(null!=spreadingSummaryData.getStartDate()?(spreadingSummaryData.getStartDate()):EMPTY_STRING);
		monthlyDataVo.setDrawing_power(EMPTY_STRING);
		monthlyDataVo.setCurrency_code(null!=spreadingSummaryData.getCurrency()?spreadingSummaryData.getCurrency():EMPTY_STRING);
		monthlyDataVo.setTax_pay_indicator(EMPTY_STRING);
		monthlyDataVo.setWithdrawal_count(null!=spreadingSummaryData.getCountWithdrawals()?String.valueOf(spreadingSummaryData.getCountWithdrawals()):EMPTY_STRING);
		monthlyDataVo.setInward_check_return_count(null!=spreadingSummaryData.getTotalNumberInwardCheckReturn()?String.valueOf(spreadingSummaryData.getTotalNumberInwardCheckReturn()):EMPTY_STRING);
		monthlyDataVo.setInward_check_return_amount(null!=spreadingSummaryData.getTotalDollarInwardCheckReturn()?String.valueOf(spreadingSummaryData.getTotalDollarInwardCheckReturn()):EMPTY_STRING);
		monthlyDataVo.setOutward_check_return_amount(null!=spreadingSummaryData.getTotalDollarOutwardCheckReturn()?String.valueOf(spreadingSummaryData.getTotalDollarOutwardCheckReturn()):EMPTY_STRING);
		monthlyDataVo.setOpening_balance_amount(null!=spreadingSummaryData.getOpenBalance()?String.valueOf(spreadingSummaryData.getOpenBalance()):EMPTY_STRING);
		monthlyDataVo.setMinimum_balance_amount(null!=spreadingSummaryData.getMinimumBalance()?String.valueOf(spreadingSummaryData.getMinimumBalance()):EMPTY_STRING);  
		monthlyDataVo.setTotal_withdrawals_amount(null!=spreadingSummaryData.getTotalWithdrawals()?String.valueOf(spreadingSummaryData.getTotalWithdrawals()):EMPTY_STRING);
		monthlyDataVo.setOutward_check_return_count(null!=spreadingSummaryData.getTotalDollarOutwardCheckReturn()?String.valueOf(spreadingSummaryData.getTotalDollarOutwardCheckReturn()):EMPTY_STRING);
		monthlyDataVo.setStatement_end_date(null!=spreadingSummaryData.getEndDate()?(spreadingSummaryData.getEndDate()):EMPTY_STRING);   
		monthlyDataVo.setDeposits_count(null!=spreadingSummaryData.getCountDeposits()?String.valueOf(spreadingSummaryData.getCountDeposits()):EMPTY_STRING);      
		monthlyDataVo.setTotal_deposits_amount(null!=spreadingSummaryData.getTotalDeposits()?String.valueOf(spreadingSummaryData.getTotalDeposits()):EMPTY_STRING);
		monthlyDataVo.setInterest_serving_days_count(EMPTY_STRING);
		monthlyDataVo.setStatement_period(null!=spreadingSummaryData.getStatementPeriod()?spreadingSummaryData.getStatementPeriod():EMPTY_STRING);
		monthlyDataVo.setTransaction_data(getTransactionVo(spreadingTransactionDataRepository.findBySummaryLevelDataId(spreadingSummaryData.getId())));
		
		monthlyDataVoList.add(monthlyDataVo);
		}
		return monthlyDataVoList;
	}

	private List<TransactionDataVo> getTransactionVo(List<SpreadingTransactionData> spreadingTransactionDataList) {
		
		List<TransactionDataVo> transactionDataVoList=new ArrayList<>();
		
		for (SpreadingTransactionData  spreadingTransactionData : spreadingTransactionDataList) {
			
			String crDr = null!=spreadingTransactionData.getCreditOrDebit()?spreadingTransactionData.getCreditOrDebit():EMPTY_STRING;
			TransactionDataVo transactionDataVo= new TransactionDataVo();
			transactionDataVo.setTransaction_id(null!=spreadingTransactionData.getTransactionId()?spreadingTransactionData.getTransactionId():EMPTY_STRING);
			transactionDataVo.setDescription(null!=spreadingTransactionData.getDescription()?spreadingTransactionData.getDescription():EMPTY_STRING);
			transactionDataVo.setTransaction_amount(null!=spreadingTransactionData.getTransactionAmount()?String.valueOf(spreadingTransactionData.getTransactionAmount()):EMPTY_STRING);
			transactionDataVo.setPosted_order(null!=spreadingTransactionData.getPostedOrder()?spreadingTransactionData.getPostedOrder():EMPTY_STRING);
			transactionDataVo.setTransaction_date(null!=spreadingTransactionData.getTransactionDate()?(spreadingTransactionData.getTransactionDate()):EMPTY_STRING);
			transactionDataVo.setTransaction_type(crDr);
			transactionDataVo.setAvailable_balance_amount(null!=spreadingTransactionData.getAvailableBalance()?String.valueOf(spreadingTransactionData.getAvailableBalance()):EMPTY_STRING);
			transactionDataVo.setCheck_number(null!=spreadingTransactionData.getCheckNumber()?spreadingTransactionData.getCheckNumber():EMPTY_STRING);
			transactionDataVo.setTransaction_amount_currency_code(null!=spreadingTransactionData.getTransactionCurrencyCode()?spreadingTransactionData.getTransactionCurrencyCode():EMPTY_STRING);
			transactionDataVoList.add(transactionDataVo);
		}
		return transactionDataVoList;
	}
	
	private String formatDateToMMDDYYYY(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(date);
	}
	
	private String formatDateToMMDDYYYYHHMMSS(Date date) {
		if(null==date) return null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:MM:SS");
		return formatter.format(date);
	}

	@Override
	public CLToURSResponseVo postSpreadingStatusToCL(String se10) {
		prepareCLRequestData(se10);
		return callCLPostAPI(null);
	}
	
	private void prepareCLRequestData(String se10) {
		
	}

	private CLToURSResponseVo callCLPostAPI(String postEntity)  {
		AuthToken authToken = null;
		int count=0;
		do{
			try {
			HttpPost postToken = getAuthHeaderForCLTokenApi();
			authToken = apiGatewayService.callPostApi(postToken, AuthToken.class);
			
			if (null != authToken) {
			
				logger.info("Access Token is ="+authToken.getAccess_token());
				logger.info("Start - calling CLPostAPI");
			
				HttpPost post = getPostForCLPostApi(authToken.getAccess_token());
				post.setEntity(new StringEntity(postEntity.toString(), "UTF-8"));
				CLToURSResponseVo clResponseModel = apiGatewayService.callPostApi(post, CLToURSResponseVo.class);
				logger.info("End - CLPostAPI");
				return clResponseModel;
			}else 
				logger.info("Access Token is null");

			}catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			count++;
		
		}while(count< 3);
		
		return null;
	}

	private HttpPost getAuthHeaderForCLTokenApi(){
		HttpPost post = new HttpPost(clTokenUrl);
		post.setHeader("grant_type", "password");
		post.setHeader("client_id", clientId);
		post.setHeader("client_secret", clClientSecret);
		post.setHeader("username", clClientUserName);
		post.setHeader("password",clClientPassword);
		return post;
	}
	
	private HttpPost getPostForCLPostApi(String token){
		HttpPost post = new HttpPost(clAPIUrl);
		post.setHeader("Authorization", "Bearer "+token);
		return post;
	}

}
