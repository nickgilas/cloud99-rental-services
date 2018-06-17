package com.cloud99.rental.config.security.oauth;

import com.cloud99.rental.config.security.Cloud99BasicAuthEntryPoint;
import com.cloud99.rental.config.security.Cloud99SimpleUrlAuthenticationSuccessHandler;
import com.cloud99.rental.config.security.SecurityRole;
import com.cloud99.rental.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@Order(value = 10)
@ComponentScan(basePackages = "com.cloud99.rental.config.security")
@EnableWebSecurity
public class Oauth2SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Cloud99BasicAuthEntryPoint entryPoint;
	
	@Autowired
	private UserService userService;

	@Autowired
    private ClientDetailsService clientDetailsService;
	
	@Autowired
	private Cloud99SimpleUrlAuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userService);
		super.configure(auth);
	}
	
	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("admin").password("admin").roles(SecurityRole.ADMIN.toString()).and()
        .withUser("user").password("user").roles(SecurityRole.USER.toString());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {

		// TODO - NG - add roles here
        http
        	.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and().exceptionHandling().authenticationEntryPoint(entryPoint)
        	.and().anonymous().disable()
	        .authorizeRequests()
    		.antMatchers( "/oauth/token", "/user/registration", "/rental/login").permitAll();
//				.successHandler(successHandler)
//				.failureHandler(new SimpleUrlAuthenticationFailureHandler());
        
		// .accessDeniedHandler(new Cloud99AccessDeniedHandler());
//		http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class)
//				.addFilterBefore(new ManagementEndpointAuthenticationFilter(authenticationManager()),
//						BasicAuthenticationFilter.class);
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
 
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }
     
    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }
     
}
