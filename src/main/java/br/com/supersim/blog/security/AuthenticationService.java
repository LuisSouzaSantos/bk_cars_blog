package br.com.supersim.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.supersim.blog.entity.User;
import br.com.supersim.blog.exception.UserException;
import br.com.supersim.blog.service.UserService;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = userService.getUserByEmail(username);
			return user;
		} catch (UserException e) {
			throw new UsernameNotFoundException("INVALID_CREDENTIALS");
		}
	}

}
