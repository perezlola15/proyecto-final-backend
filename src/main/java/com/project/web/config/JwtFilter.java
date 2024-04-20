package com.project.web.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 1. Validar que el header sea un Authorization valido
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}

		// 2. Validar el JWT
		String jwt = authHeader.split(" ")[1].trim();

		if (!this.jwtUtils.isValid(jwt)) {
			filterChain.doFilter(request, response);
			return;
		}

		// 3. Cargamos el usuario desde el servicio
		String username = this.jwtUtils.getUsername(jwt);
		User user = (User) this.userDetailsService.loadUserByUsername(username);
		
		// 4. Cargar el usuario en el contexto de Spring
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(token);
		filterChain.doFilter(request, response);
	}

}
