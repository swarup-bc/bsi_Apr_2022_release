package com.urs.bsi.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.sql.DataSource;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig { 
    
    
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean
    @Primary
    public DataSource dataSource() {
    	HikariDataSource hds = primaryDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    	hds.setMaxLifetime(30000);
    	hds.setIdleTimeout(0);
    	return hds;
    }
    
    @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
	    char[] password = "123456789".toCharArray();

	    SSLContext sslContext = SSLContextBuilder.create()
	            .loadKeyMaterial(keyStore("bc_ai.jks", password), password)
	            .loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();

	    HttpClient client = HttpClients.custom().setSSLContext(sslContext).build();
	    return builder
	            .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client))
	            .build();
	}

	 private KeyStore keyStore(String file, char[] password) throws Exception {
	    KeyStore keyStore = KeyStore.getInstance("JKS");
//	    File key = ResourceUtils.getFile(file);
	    ClassLoader cl = DataSourceConfig.class.getClassLoader();
	    try (InputStream in = cl.getResourceAsStream(file)) {
	        keyStore.load(in, password);
	    }
	    return keyStore;
	}
    
    
//    @Bean
//    @ConfigurationProperties("spring.datasource.secondary")
//    public DataSourceProperties secondaryDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//    
//    @Bean(name="secondaryDataSource")
//    public DataSource cardholderDataSource() {
//    	HikariDataSource hds = secondaryDataSourceProperties().initializeDataSourceBuilder()
//                .type(HikariDataSource.class).build();
//    	hds.setMaxLifetime(30000);
//    	hds.setIdleTimeout(0);
//    	return hds;
//    }
}

