package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.UserService;

@SuppressWarnings("deprecation")
@Configuration
//@Order(1)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	@Bean
	public static BCryptPasswordEncoder userPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth= new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(userPasswordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers(
				"/",
				"/js/**",
				"/css/**",
				"/img/**",
				"/registration",
				"/admin/**",
				
				"/product"
				)
		.permitAll()
		.antMatchers("/bidding",
				"/buy",
				"/bid",
				"/checkout")
		.hasAuthority("USER")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/")
		.failureUrl("/?error=true")
		.defaultSuccessUrl("/?success=true")
		.permitAll()
		.usernameParameter("email")
		.passwordParameter("password")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/?loggedout")
		.and()
		.exceptionHandling()
		.and()
		.csrf()
		.disable();
	}
}
