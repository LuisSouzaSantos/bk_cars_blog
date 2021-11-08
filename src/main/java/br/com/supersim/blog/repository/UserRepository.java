package br.com.supersim.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supersim.blog.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);

}
