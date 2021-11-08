package br.com.supersim.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supersim.blog.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
