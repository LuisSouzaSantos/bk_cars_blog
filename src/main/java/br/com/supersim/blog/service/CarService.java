package br.com.supersim.blog.service;

import java.security.Principal;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import br.com.supersim.blog.entity.Car;
import br.com.supersim.blog.exception.BrandException;
import br.com.supersim.blog.exception.CarException;
import br.com.supersim.blog.exception.UserException;

public interface CarService {

    public Car save(Car car, MultipartFile multipartFile, Principal requestingUser) throws UserException, CarException, BrandException;
	
	public void delete(Long carId, Principal requestingUser) throws CarException, UserException;
	
	public Car update(Car car, MultipartFile multipartFile, Principal requestingUser) throws UserException, CarException, BrandException;;
	
	public List<Car> getAllCars();
	
	public Car getCarById(Long id) throws CarException;
	
	public ResponseEntity<ByteArrayResource> getPhotoDownloadByKey(String photoKey);
	
	public List<Car> getCarsByBrandId(Long id) throws CarException, BrandException;
	
}
