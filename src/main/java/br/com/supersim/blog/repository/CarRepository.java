package br.com.supersim.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supersim.blog.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	
	public Car findByPhotoKey(String photoKey);

}
