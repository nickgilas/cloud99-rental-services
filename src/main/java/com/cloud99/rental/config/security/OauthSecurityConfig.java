package com.cloud99.rental.config.security;

import com.cloud99.rental.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class OauthSecurityConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
 
	@Autowired
	private UserService userService;
	
    @Override
    public void configure(
      AuthorizationServerSecurityConfigurer oauthServer) 
      throws Exception {
        oauthServer
          .tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()");
    }
 
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) 
      throws Exception {
        clients.withClientDetails(userService)
          .withClient("sampleClientId")
          .authorizedGrantTypes("implicit")
          .scopes("read")
          .autoApprove(true)
          .authorizedGrantTypes("password","authorization_code", "refresh_token");
         
    }
 
    @Override
    public void configure(
			AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
  
        endpoints
          .tokenStore(tokenStore())
          .authenticationManager(authenticationManager);
    }
 
    @Bean
    public TokenStore tokenStore() {
		return new JdbcTokenStore(null);
    }
}
