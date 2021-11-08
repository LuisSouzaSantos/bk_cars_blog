package br.com.supersim.blog.DTO;

import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.supersim.blog.entity.Role;
import br.com.supersim.blog.entity.User;

public class UserDTO {
	
	private Long id;
	@NotBlank(message = "User name is required")
	private String name;
	@NotBlank(message = "User email is required")
	private String email;
	private List<Role> roleList;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.roleList = user.getRoleList();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public User transformToUser() {
		return new User(this);
	}
	
}
