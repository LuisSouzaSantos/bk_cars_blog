package br.com.supersim.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supersim.blog.DTO.LoginDTO;
import br.com.supersim.blog.entity.Login;
import br.com.supersim.blog.service.AuthService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping
	public ResponseEntity<LoginDTO> login(@RequestBody @Valid Login login) {
		return authService.login(login);
	}

}
