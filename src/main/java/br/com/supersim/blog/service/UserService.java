package br.com.supersim.blog.service;

import br.com.supersim.blog.DTO.UserDTO;
import br.com.supersim.blog.entity.User;
import br.com.supersim.blog.exception.UserException;

public interface UserService {
	
	public UserDTO save(User user) throws UserException;
	
	public void remove(Long id) throws UserException;
	
	public UserDTO update(User user);
	
	public UserDTO getUserDTOByEmail(String email) throws UserException;
	
	public User getUserByEmail(String email) throws UserException;
	
	public User getUserById(Long id);

}
