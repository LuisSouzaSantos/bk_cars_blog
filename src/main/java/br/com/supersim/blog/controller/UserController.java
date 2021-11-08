package br.com.supersim.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supersim.blog.DTO.UserDTO;
import br.com.supersim.blog.entity.User;
import br.com.supersim.blog.exception.UserException;
import br.com.supersim.blog.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public UserDTO save(@RequestBody @Valid User user) throws UserException {
		return userService.save(user);
	}
	
	@GetMapping("/{email}")
	public UserDTO getUserByEmail(@PathVariable String email) throws UserException {
		return userService.getUserDTOByEmail(email);
	}

}
