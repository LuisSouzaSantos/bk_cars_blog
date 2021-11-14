package br.com.supersim.blog.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supersim.blog.entity.Brand;
import br.com.supersim.blog.exception.BrandException;
import br.com.supersim.blog.exception.UserException;
import br.com.supersim.blog.service.BrandService;

@CrossOrigin
@RestController
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@PostMapping
	public Brand save(@RequestBody @Valid Brand brand, Principal requestingUser) throws BrandException, UserException  {
		return brandService.save(brand, requestingUser);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id, Principal requestingUser) throws BrandException, UserException {
		brandService.delete(id, requestingUser);
	}
	
	@PutMapping
	public Brand update(@RequestBody @Valid Brand brand, Principal requestingUser) throws BrandException, UserException  {
		return brandService.update(brand, requestingUser);
	}
	
	@GetMapping("/all")
	public List<Brand> getAllBrands() {
		return brandService.getAllBrands();
	}
	
}
