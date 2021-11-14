package br.com.supersim.blog.controller;

import java.io.IOException;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.supersim.blog.entity.Car;
import br.com.supersim.blog.exception.BrandException;
import br.com.supersim.blog.exception.CarException;
import br.com.supersim.blog.exception.UserException;
import br.com.supersim.blog.service.CarService;

@CrossOrigin
@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Car save(
			@RequestPart(value = "publication", required = true) @Valid Car car, 
			@RequestPart(value = "file", required = true) MultipartFile multipartFile,
			Principal requestingUser) throws UserException, CarException, BrandException {
		return carService.save(car, multipartFile, requestingUser);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id, Principal requestingUser) throws CarException, UserException {
		carService.delete(id, requestingUser);
	}
	
	@GetMapping("photo/{photoKey}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable String photoKey) throws IllegalStateException, IOException {
		return carService.getPhotoDownloadByKey(photoKey);
		
	}
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Car edit(
			@RequestPart(value = "publication", required = true) @Valid Car car, 
			@RequestPart(value = "file", required = false) MultipartFile multipartFile,
			Principal requestingUser) throws UserException, CarException, BrandException {
		return carService.update(car, multipartFile, requestingUser);
	}
	
//	@GetMapping("/all")
//	public List<PublicationDTO> allPublicationByUserEmail(){
//		return publicationService.getAllPublications();
//	}

}
