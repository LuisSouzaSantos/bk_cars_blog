package br.com.supersim.blog.DTO;

import javax.validation.constraints.NotBlank;

import br.com.supersim.blog.entity.Login;
import br.com.supersim.blog.entity.Token;

public class LoginDTO {
	
	@NotBlank
	private String email;
	private Token token;
	
	public LoginDTO() {
		
	}
	
	public LoginDTO(Login login) {
		this.email = login.getEmail();
		this.token = login.getToken();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	
}
