package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails user1 = User.withUsername("hieu")
				.password("$2a$10$SsH2qBKiwPJCzFWbNBVeDOlykok/LOUzxFOVXKwOI1GFC24lJ67He")
				.roles("Admin")
				.build();
		return new InMemoryUserDetailsManager(user1);
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//		.cors()
//		.and()
		.authorizeRequests()
		.anyRequest().permitAll()
//		.and()
//		.formLogin().loginPage("/login")
//		.permitAll()
//		.and()
//		.logout().permitAll()
//		 .and()
//		.httpBasic()
		.and()
		.csrf().disable();
	
	}

	
}
