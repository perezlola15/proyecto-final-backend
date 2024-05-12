package com.project.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/auth/login")
				.allowedOrigins("http://localhost:4200", "http://localhost:3000")
				.allowedMethods("GET", "POST")
				.exposedHeaders("*")
				.allowCredentials(true);
				
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200", "http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
				.allowCredentials(true);
				
				registry.addMapping("/ordersLine/*")
				.allowedOrigins("http://localhost:4200", "http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
				.allowCredentials(true);
			}
		};
	}
}
