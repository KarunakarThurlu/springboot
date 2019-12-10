package com.app.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SeccurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePwd());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		/*http.authorizeRequests().antMatchers("/app/**").authenticated().anyRequest().permitAll()
		.and()
		.authorizeRequests().antMatchers("/secure/**").authenticated().anyRequest().hasAnyRole("ADMIN")
		.and()
		.formLogin().permitAll();
		http.authorizeRequests()
		.antMatchers("/app/**").hasAnyRole("USER","ADMIN")
		 .antMatchers("/secure/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.permitAll();*/
		
	}
}
