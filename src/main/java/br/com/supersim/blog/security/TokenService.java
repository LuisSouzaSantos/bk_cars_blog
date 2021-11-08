package br.com.supersim.blog.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.supersim.blog.entity.Properties;
import br.com.supersim.blog.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Autowired
	private Properties properties;
	
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date today = new Date();
		Date expirationDate = new Date(today.getTime() + 
								Long.parseLong(properties.getJwtExpiration()));
		
		return Jwts.builder()
				.setIssuer("Blog supersim")
				.setSubject(user.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, properties.getJwtSecret())
				.compact();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser()
				.setSigningKey(properties.getJwtSecret())
				.parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public Long getUserId(String token) {
		Claims claims = Jwts.parser()
							.setSigningKey(properties.getJwtSecret())
							.parseClaimsJws(token)
							.getBody();
		return Long.parseLong(claims.getSubject());
	}

}
