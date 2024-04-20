package com.project.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	@SuppressWarnings(value = "all")
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // CSRF disable
				.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeHttpRequests()
				// Permitir a todos hacer login
				.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
				
				.requestMatchers(HttpMethod.GET, "/staffs").permitAll()
				.requestMatchers(HttpMethod.GET, "/staffs/*").permitAll()
				.requestMatchers(HttpMethod.GET, "/dishes").permitAll()
				.requestMatchers(HttpMethod.GET, "/dishes/*").permitAll()
				.requestMatchers(HttpMethod.GET, "/orders").permitAll()
				.requestMatchers(HttpMethod.GET, "/orders/*").permitAll()
				
				.requestMatchers(HttpMethod.POST, "/staffs/*").permitAll()
				.requestMatchers(HttpMethod.POST, "/dishes/*").permitAll()
				.requestMatchers(HttpMethod.DELETE, "/staffs/*").permitAll()
				.requestMatchers(HttpMethod.DELETE, "/dishes/*").permitAll()
				.requestMatchers(HttpMethod.PUT, "/staffs/*").permitAll()
				.requestMatchers(HttpMethod.PUT, "/dishes/*").permitAll()
				
				.requestMatchers(HttpMethod.GET, "/categories/*").permitAll()
				.requestMatchers(HttpMethod.GET, "/categories").permitAll()
				.requestMatchers(HttpMethod.POST, "/categories/*").permitAll()
				.requestMatchers(HttpMethod.POST, "/categories").permitAll()
				/*
				// Control en categories
				.requestMatchers(HttpMethod.GET, "/categories/*").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
				
				// Control en dishes
				.requestMatchers(HttpMethod.GET, "/dishes/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.POST, "/dishes/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/dishes/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/dishes/**").hasRole("ADMIN")
				
				// Control en orders
				.requestMatchers(HttpMethod.GET, "/orders/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.POST, "/orders/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/orders/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.PUT, "/orders/**").hasAnyRole("USER", "ADMIN")
				
				// Control en ordersLine
				.requestMatchers(HttpMethod.GET, "/ordersLine/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.POST, "/ordersLine/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/ordersLine/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.PUT, "/ordersLine/**").hasAnyRole("USER", "ADMIN")
				
				// Control en staff
				.requestMatchers(HttpMethod.GET, "/staff/*").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.POST, "/staff/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/staff/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/staff/**").hasRole("ADMIN") */
				
				.anyRequest().authenticated().and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		// .httpBasic() ;

		return http.build();
	}

}
