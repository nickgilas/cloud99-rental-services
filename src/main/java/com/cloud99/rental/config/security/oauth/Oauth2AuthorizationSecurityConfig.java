package com.cloud99.rental.config.security.oauth;

import com.cloud99.rental.config.security.SecurityRole;
import com.cloud99.rental.dataCreation.DataCreator;
import com.cloud99.rental.domain.document.security.SecurityAccess.Access;
import com.cloud99.rental.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
@ComponentScan
public class Oauth2AuthorizationSecurityConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${oauth.client.id}")
	private String oauthClientId;

	@Value("${oauth.client.secret}")
	private String oauthClientSecret;
 
	private static String REALM = "CLOUD99_OAUTH_REALM";
    
    @Autowired
    private TokenStore tokenStore;
 
    @Autowired
    private UserApprovalHandler userApprovalHandler;
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private DataCreator data;

	@Autowired
	private DataCreator dataCreator;

    @Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        oauthServer.realm(REALM + "/client");
//          .tokenKeyAccess("permitAll()")
//          .checkTokenAccess("isAuthenticated()");
    }
 
    @Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.withClientDetails(userService)
				.withClient(oauthClientId)
				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
				.authorities(SecurityRole.USER.toString(), SecurityRole.ADMIN.toString())
				.scopes(Access.CREATE.toString(), Access.READ.toString(), Access.UPDATE.toString(),
						Access.DELETE.toString())
				// TODO - need to generate a secure secret
				.secret("cloud99Secret").accessTokenValiditySeconds(900) // access token valid for 15 minutes
				.refreshTokenValiditySeconds(1800); // refresh token is valid for 30 minutes
         
    }
 
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

    @Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
  
        endpoints
          .tokenStore(tokenStore())
				.authenticationManager(authenticationManager).userApprovalHandler(userApprovalHandler);
    }

    @Bean
    public TokenStore tokenStore() {
		TokenStore store = new InMemoryTokenStore();

		// add new token to store, this is an unlimited token to use during testing
		// SecuredUser user = new SecuredUser(dataCreator.createUser(),
		// dataCreator.createFeatures(), dataCreator.crea)
//		AuthenticatedUserToken token = new AuthenticatedUserToken(user);
		// UnlimitedAuthentication unlimitedAuthentication = new
		// UnlimitedAuthentication();
//		OAuth2Request request = new OAuth2Request(new HashMap<String, String>(), oauthClientId,
//				userService.loadClientByClientId("myClientId").getAuthorities(), true, user.getSecurityRoles().iterator().next().getAuthority(), null, "",
//				new HashSet<String>(), new HashMap<String, Serializable>());
//		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(request, unlimitedAuthentication);
//		DefaultOAuth2AccessToken oauthToken = new DefaultOAuth2AccessToken("tokenValue");
		// Need to create these dam things
		// OAuth2AccessToken token, OAuth2Authentication authentication
//		store.storeAccessToken(oauthToken, oAuth2Authentication);

		return store;
    }
}
