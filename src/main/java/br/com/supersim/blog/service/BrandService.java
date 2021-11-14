package br.com.supersim.blog.service;

import java.security.Principal;
import java.util.List;

import br.com.supersim.blog.entity.Brand;
import br.com.supersim.blog.exception.BrandException;
import br.com.supersim.blog.exception.UserException;

public interface BrandService {

	public Brand save(Brand brand, Principal requestingUser) throws BrandException, UserException;
	
	public void delete(Long brandId, Principal requestingUser) throws BrandException, UserException;
	
	public Brand update(Brand brand, Principal requestingUser) throws BrandException, UserException;
	
	public List<Brand> getAllBrands();
	
	public Brand getBrandById(Long id) throws BrandException;
	
}
