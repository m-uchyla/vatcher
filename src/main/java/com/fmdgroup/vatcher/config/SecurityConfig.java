package com.fmdgroup.vatcher.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.fmdgroup.vatcher.security.AuthProviderImpl;

//@Compoment
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final AuthProviderImpl authProvider;
	
	
	@Autowired
	public SecurityConfig(AuthProviderImpl authProvider) {
		
		this.authProvider = authProvider;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// configure Spring Security (not default login page )
		// configure roles
		
		http
		.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
		.anyRequest().hasAnyRole("USER","ADMIN")
		.and()
		.formLogin().loginPage("/auth/login")
		.loginProcessingUrl("/process_login")
		.defaultSuccessUrl("/users", true)
		.failureUrl("/auth/login?error")
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/auth/login");
	}

	// setting the authentication
	protected void configure(AuthenticationManagerBuilder auth) {
		System.out.println("Confidure / SecurityConfig");
		auth.authenticationProvider(authProvider);

}
	
}
