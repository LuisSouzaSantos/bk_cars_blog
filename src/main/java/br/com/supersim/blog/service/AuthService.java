package br.com.supersim.blog.service;

import org.springframework.http.ResponseEntity;

import br.com.supersim.blog.DTO.LoginDTO;
import br.com.supersim.blog.entity.Login;

public interface AuthService {
	
	public ResponseEntity<LoginDTO> login(Login login);

}
