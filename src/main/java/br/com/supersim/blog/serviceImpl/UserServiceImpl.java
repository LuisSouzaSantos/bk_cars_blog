package br.com.supersim.blog.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.supersim.blog.DTO.UserDTO;
import br.com.supersim.blog.entity.User;
import br.com.supersim.blog.exception.UserException;
import br.com.supersim.blog.repository.UserRepository;
import br.com.supersim.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO save(User newUser) throws UserException {
		
		User user = userRepository.findByEmail(newUser.getEmail());
		
		if(user != null) { throw new UserException("USER_ALREADY_EXISTS"); }
		
		return new UserDTO(userRepository.save(newUser));
	}

	@Override
	public void remove(Long id) throws UserException {
		
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent() == false) { throw new UserException("INVALID_USER"); }
		
		userRepository.delete(user.get());
		
	}

	@Override
	public UserDTO update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserDTOByEmail(String email) throws UserException {
		
		User user = userRepository.findByEmail(email);
		
		if(user == null) { throw new UserException("INVALID_USER_EMAIL");}
		
		return new UserDTO(user);
	}
	
	@Override
	public User getUserByEmail(String email) throws UserException {
		User user = userRepository.findByEmail(email);
		
		if(user == null) { throw new UserException("INVALID_USER_EMAIL");}
		
		return user;
	}
	

	@Override
	public User getUserById(Long id) {
		
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent() == false) { return null;}
		
		return user.get();
	}

}
