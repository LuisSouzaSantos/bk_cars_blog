package br.com.supersim.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supersim.blog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findByName(String name); 

}
