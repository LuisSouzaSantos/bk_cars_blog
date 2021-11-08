package br.com.supersim.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import br.com.supersim.blog.DTO.LoginDTO;
import br.com.supersim.blog.entity.Login;
import br.com.supersim.blog.entity.Token;
import br.com.supersim.blog.security.TokenService;
import br.com.supersim.blog.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public ResponseEntity<LoginDTO> login(Login login) {
		
		UsernamePasswordAuthenticationToken uPasswordAuthenticationToken = login.converter();
		
		try {
			Authentication authentication = authenticationManager.authenticate(uPasswordAuthenticationToken);
			String token = tokenService.generateToken(authentication);
			login.setToken(new Token(token, "Bearer"));
			return ResponseEntity.ok(new LoginDTO(login));
		}catch(AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	
	}

}
