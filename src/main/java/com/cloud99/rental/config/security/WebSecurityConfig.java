package com.cloud99.rental.config.security;

import com.cloud99.rental.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableAuthorizationServer
@Order(value = 10)
@ComponentScan(basePackages = "com.cloud99.rental.config.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Cloud99BasicAuthEntryPoint entryPoint;
	
	@Autowired
	private UserService userService;


	@Autowired
	private Cloud99SimpleUrlAuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userService);
		super.configure(auth);
	}

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return userService;
	}
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {

		// TODO - NG - add roles here
        http.authorizeRequests()
        		.antMatchers("/user/registration").permitAll()
				.antMatchers("/*").hasAnyRole(SecurityRole.USER.name())
				.antMatchers("/rental/login").permitAll()
				.and().httpBasic()
				.and().logout().logoutUrl("/rental/logout")
				.invalidateHttpSession(true)
				.and().csrf().disable().exceptionHandling().authenticationEntryPoint(entryPoint).and()
				.userDetailsService(userDetailsService())
				// TODO - NG - not sure this is needed since we dont have a "login" page in this app
				.formLogin().loginPage("/login").permitAll()
				.successHandler(successHandler)
				.failureHandler(new SimpleUrlAuthenticationFailureHandler());
        
		// .accessDeniedHandler(new Cloud99AccessDeniedHandler());
    }

}
