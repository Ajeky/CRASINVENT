/**
 * 
 */
package com.salesianostriana.damcrasinvent.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Álvaro Márquez
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserDetailsService userDetailsService;
	
	CustomSuccessHandler customSuccessHandler;
	
	public SecurityConfig(UserDetailsService userDetailsService, CustomSuccessHandler customSuccessHandler) {
		this.userDetailsService = userDetailsService;
		this.customSuccessHandler = customSuccessHandler;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/css/**","/js/**","/webjars/**", "/h2-console/**", "/", "/newUser/**").permitAll()
						.antMatchers("/admin/**").hasAnyRole("ADMIN")
						.antMatchers("/user/**").hasAnyRole("ADMIN", "USER", "PREMIUMUSER")
						.anyRequest().authenticated()
						.and()
				.formLogin()
						.loginPage("/login")
						.permitAll()
						.successHandler(customSuccessHandler)
						.and()
				.logout()
						.logoutUrl("/logout")
						.permitAll()
						.logoutSuccessUrl("/")
						.and()
				.exceptionHandling()
						.accessDeniedPage("/acceso-denegado");
		
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
}
