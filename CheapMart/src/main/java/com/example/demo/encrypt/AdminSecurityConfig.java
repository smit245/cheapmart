package com.example.demo.encrypt;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@Order(2)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin@gmail.com").password("abcde").roles("Admin");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(
				"/Admin/js/**",
				"/Admin/css/**",
				"/Admin/img/**"
				)
		.permitAll()
		.antMatchers("/admin/AdminHome",
				"/admin/AdminProduct",
				"/admin/AdminOrders",
				"/admin/AdminUser")
		.hasAuthority("Admin")
		.anyRequest()
		.authenticated().and().formLogin().loginPage("/admin/login")
		.failureUrl("/admin/login?error=true")
		.defaultSuccessUrl("/admin/AdminHome", true)
		.permitAll().and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/admin/login?loggedout")
		.and()
		.exceptionHandling()
		.and()
		.csrf()
		.disable();

	}

}