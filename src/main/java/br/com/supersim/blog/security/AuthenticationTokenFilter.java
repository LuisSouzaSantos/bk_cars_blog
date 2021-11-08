package br.com.supersim.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.supersim.blog.entity.User;
import br.com.supersim.blog.service.UserService;

public class AuthenticationTokenFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private UserService userService;
	
	public AuthenticationTokenFilter(TokenService tokenService, UserService userService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = retrieveToken(request);
		boolean isValidToken = tokenService.isTokenValid(token);
		
		if(isValidToken) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void authenticateClient(String token) {
		Long userId = tokenService.getUserId(token);
		User user = userService.getUserById(userId);
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
	
	
	private String retrieveToken(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}