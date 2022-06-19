package com.example.demo.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@SuppressWarnings("deprecation")
@Configuration
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin@gmail.com").password("abcde").authorities("ADMIN");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(
				"/",
				"/admin/js/**",
				"/admin/css/**",
				"/admin/img/**",
				"/js/**",
				"/css/**",
				"/img/**",
				"/registration"
				)
		.permitAll()
		.antMatchers("/admin/AdminHome",
				"/admin/AdminProduct",
				"/admin/AdminOrders",
				"/admin/AdminUser")
		.hasAuthority("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/admin/login")
		.defaultSuccessUrl("/admin/AdminHome")
		.failureUrl("/admin/login?error")
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