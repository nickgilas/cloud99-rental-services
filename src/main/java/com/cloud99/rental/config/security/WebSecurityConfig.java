package com.cloud99.rental.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity(debug = true)
@Order(value = 10)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Cloud99BasicAuthEntryPoint entryPoint;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// TODO - NG - change to use a custom UserDetailService and retrieve data from redis
        auth.inMemoryAuthentication()
          .withUser("user").password("password")
          .authorities("ROLE_USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
       
        // .accessDeniedHandler(new Cloud99AccessDeniedHandler())
//          .anyRequest().authenticated()
				.anyRequest().permitAll();
//          .and()
//          .httpBasic()
//          .authenticationEntryPoint(entryPoint);
//          .failureHandler(new SimpleUrlAuthenticationFailureHandler());
 
//        http.addFilterAfter(new DebugFilter(http.get), BasicAuthenticationFilter.class);
    }

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
