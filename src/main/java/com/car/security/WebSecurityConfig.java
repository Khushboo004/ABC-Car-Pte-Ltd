package com.car.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService userDetailsService() {
		return new  UserDetailsServiceImpl();
		
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider  authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	
		.authorizeRequests()
		
		.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
		.antMatchers("/user/**").hasAnyAuthority("USER")
		.antMatchers("/addCar/**").hasAnyAuthority("ADMIN")
		.antMatchers("/edit/**").hasAnyAuthority("ADMIN")
		.antMatchers("/store/**").hasAnyAuthority("ADMIN","USER")
		.antMatchers("/**").hasAnyAuthority("ALL")
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.loginPage("/login")
//		.loginProcessingUrl("/dologin")
//		.defaultSuccessUrl("/user/user-home")
		.successHandler(successHandler)
//		.failureUrl("/login-fail")
		.and()
		.csrf().disable()
		.exceptionHandling().accessDeniedPage("/403")
		;
		
	}
	@Autowired
	private LoginSuccessHandler successHandler;

	
	
	


}

