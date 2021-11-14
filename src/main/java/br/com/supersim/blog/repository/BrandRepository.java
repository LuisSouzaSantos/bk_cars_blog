package br.com.supersim.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supersim.blog.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{

	public Brand findByName(String name);
	
}
