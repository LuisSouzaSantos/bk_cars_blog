package br.com.supersim.blog.serviceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.supersim.blog.DTO.UserDTO;
import br.com.supersim.blog.entity.Brand;
import br.com.supersim.blog.exception.BrandException;
import br.com.supersim.blog.exception.UserException;
import br.com.supersim.blog.repository.BrandRepository;
import br.com.supersim.blog.service.BrandService;
import br.com.supersim.blog.service.UserService;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Brand save(Brand brand, Principal requestingUser) throws BrandException, UserException {
		if(requestingUser.getName() == null) { throw new BrandException("Invalid user publication");}
		
		UserDTO userPublication = userService.getUserDTOByEmail(requestingUser.getName());
		
		if(userPublication == null) { throw new BrandException("Invalid user publication"); }
		
		Brand retrievedBrandByName = getBrandByName(brand.getName());
		
		if(retrievedBrandByName != null) { throw new BrandException("Brand already exists"); }
		
		return brandRepository.save(brand);
	}

	@Override
	public void delete(Long brandId, Principal requestingUser) throws BrandException, UserException {
		if(requestingUser.getName() == null) { throw new BrandException("Invalid user publication");}
		
		UserDTO userPublication = userService.getUserDTOByEmail(requestingUser.getName());
		
		if(userPublication == null) { throw new BrandException("Invalid user publication"); }
		
		brandRepository.deleteById(brandId);
	}

	@Override
	public Brand update(Brand brand, Principal requestingUser) throws BrandException, UserException {
		if(requestingUser.getName() == null) { throw new BrandException("Invalid user publication");}
		
		UserDTO userPublication = userService.getUserDTOByEmail(requestingUser.getName());
		
		if(userPublication == null) { throw new BrandException("Invalid user publication"); }
		
		Brand retrivedBrandByName = getBrandByName(brand.getName());
		
		if((retrivedBrandByName != null) && (retrivedBrandByName.getId().equals(brand.getId()) == false)) {
			throw new BrandException("Brand already exists");
		}
		
		return brandRepository.save(brand);
	}

	@Override
	public List<Brand> getAllBrands() {
		return brandRepository.findAll();
	}

	@Override
	public Brand getBrandById(Long id) throws BrandException {
		if(id == null) { throw new BrandException("Id cannot be null"); }
		
		Optional<Brand> brand = brandRepository.findById(id);
		
		if(brand.isPresent() == false) { throw new BrandException("Brand not found"); }
		
		return brand.get();
	}
	
	private Brand getBrandByName(String name) {
		return brandRepository.findByName(name);
	}

	
	
}
