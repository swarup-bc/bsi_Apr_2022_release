package com.urs.bsi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * 
 * Server issuing access token to clients after authentication.
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("customAuthenticationManager")
	private AuthenticationManager customAuthenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Value("${CL.client.key}")
	private String clClientKey;
	
	@Value("${CL.client.secret}")
	private String clClientSecret;
	
	@Value("${EIG.client.key}")
	private String eigClientKey;
	
	@Value("${EIG.client.secret}")
	private String eigClientSecret;
	

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(customAuthenticationManager);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
					.withClient(clClientKey).secret(passwordEncoder.encode(clClientSecret))
					.authorizedGrantTypes("client_credentials").scopes("resource-server-read", "resource-server-write")
					.accessTokenValiditySeconds(3000)
					.refreshTokenValiditySeconds(2400)
				.and()
					.withClient(eigClientKey).secret(passwordEncoder.encode(eigClientSecret))
					.authorizedGrantTypes("client_credentials").scopes("resource-server-read", "resource-server-write")
					.accessTokenValiditySeconds(3000)
					.refreshTokenValiditySeconds(2400);
	}
}
