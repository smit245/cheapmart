package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.UserService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
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
				"/video/**",
				"/sass/**",
				"/registration",
				"/admin/**",
				"/about",
				"/products",
				"/product",
				"/contact")
		.permitAll()
		.antMatchers("/bidding",
				"/buy",
				"/bid",
				"/checkout",
				"addproduct")
		.hasAuthority("USER")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/")
//		.defaultSuccessUrl("/?success=true")
		.failureUrl("/?error=true")
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
