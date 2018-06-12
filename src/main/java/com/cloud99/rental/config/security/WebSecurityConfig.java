package com.cloud99.rental.config.security;

import com.cloud99.rental.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@Order(value = 10)
@ComponentScan(basePackages = "com.cloud99.rental.config.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Cloud99BasicAuthEntryPoint entryPoint;
	
	@Autowired
	private UserService userService;


	@Autowired
	private Cloud99SimpleUrlAuthenticationSuccessHandler successHandler;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userService);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {

		// TODO - NG - add roles here
        http.authorizeRequests()
				.antMatchers("/").hasAnyRole(SecurityRole.USER.name())
				.antMatchers("/rental/login").permitAll()
				.and().httpBasic().and().logout().logoutUrl("/rental/logout")
				.invalidateHttpSession(true)
				.and().csrf().disable().exceptionHandling().authenticationEntryPoint(entryPoint).and()
				.userDetailsService(userDetailsService());

        // .accessDeniedHandler(new Cloud99AccessDeniedHandler())

//          .failureHandler(new SimpleUrlAuthenticationFailureHandler());
 
		http.addFilter(BasicAuthenticationFilter.class);
    }

}
