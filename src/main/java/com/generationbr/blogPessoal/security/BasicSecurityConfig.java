package com.generationbr.blogPessoal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImp service;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service);
		auth.inMemoryAuthentication().withUser("agda").password(passwordEncoder().encode("agda"))
				.authorities("ROLE_ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
 	protected void configure(HttpSecurity http) throws Exception {
		
 		http.authorizeRequests()
 		.antMatchers("/**").permitAll()
 		.antMatchers(HttpMethod.POST,"/usuario/login").permitAll()
 		.antMatchers(HttpMethod.POST,"/usuario/cadastrar").permitAll()
 		.anyRequest().authenticated()
 		.and().httpBasic()
 		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)	
 		.and().cors()
 		.and().csrf().disable();
 	}
}
