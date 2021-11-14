package br.com.supersim.blog.serviceImpl;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.supersim.blog.DTO.UserDTO;
import br.com.supersim.blog.entity.Brand;
import br.com.supersim.blog.entity.Car;
import br.com.supersim.blog.entity.Properties;
import br.com.supersim.blog.exception.BrandException;
import br.com.supersim.blog.exception.CarException;
import br.com.supersim.blog.exception.UserException;
import br.com.supersim.blog.repository.CarRepository;
import br.com.supersim.blog.service.BrandService;
import br.com.supersim.blog.service.CarService;
import br.com.supersim.blog.service.UserService;
import br.com.supersim.blog.utils.AmazonUtils;
import br.com.supersim.blog.utils.Utils;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BrandService bradService;
	
	@Autowired
	private Properties properties;
	
	@Override
	public Car save(Car car, MultipartFile multipartFile, Principal requestingUser) throws UserException, CarException, BrandException {
		if(requestingUser.getName() == null) { throw new CarException("Invalid requesting user");}
		
		UserDTO userPublication = userService.getUserDTOByEmail(requestingUser.getName());
		
		if(userPublication == null) { throw new CarException("Invalid requesting user"); }
		
		Brand brand = bradService.getBrandById(car.getBrand().getId());
		
		if(brand == null) { throw new CarException("Brand not found");}
		
		try {
			
			String photoKey = null;
			
			while(true) {
				photoKey = generatePhotoKey(multipartFile);
				
				if(getCarByByPhotoKey(photoKey) == null) { break; }
			}
			
			AmazonUtils.Upload(properties.getAwsKeyId(), properties.getAwsSecretKey(), properties.getBucketS3Name(), multipartFile, photoKey);
			Car newCar = new Car(car.getName(), car.getModel(), car.getYear(), car.getFipe(), car.getFuel(), photoKey, car.getBrand());
			return carRepository.save(newCar);
		}
		catch(IOException e) {
			return null;
		}
		
	}
	
	@Override
	public void delete(Long carId, Principal requestingUser) throws CarException, UserException {
		if(requestingUser.getName() == null) { throw new CarException("Invalid requesting user");}
		
		UserDTO userPublication = userService.getUserDTOByEmail(requestingUser.getName());
		
		if(userPublication == null) { throw new CarException("Invalid requesting user"); }
		
		carRepository.deleteById(carId);
	}

	@Override
	public Car update(Car car, MultipartFile multipartFile, Principal requestingUser)
			throws UserException, CarException, BrandException {
		if(requestingUser.getName() == null) { throw new CarException("Invalid requesting user");}
		
		UserDTO userPublication = userService.getUserDTOByEmail(requestingUser.getName());
		
		if(userPublication == null) { throw new CarException("Invalid requesting user"); }
		
		if(car.getId() == null) {throw new CarException("Invalid car"); }
		
		Car retrievedCar = getCarById(car.getId());
		
		Brand brand = bradService.getBrandById(car.getBrand().getId());
			
		if(brand == null) { throw new CarException("Invalid brand"); }
		
		try {
			String photoKey = null;
			
			if(multipartFile != null) {
				while(true) {
					photoKey = generatePhotoKey(multipartFile);
					
					if(getCarByByPhotoKey(photoKey) == null) { break; }
				}
				
				retrievedCar.setPhotoKey(photoKey);
				AmazonUtils.Upload(properties.getAwsKeyId(), properties.getAwsSecretKey(), properties.getBucketS3Name(), multipartFile, photoKey);
			}
			
			retrievedCar.setBrand(car.getBrand());
			retrievedCar.setFipe(car.getFipe());
			retrievedCar.setFuel(car.getFuel());
			retrievedCar.setModel(car.getModel());
			retrievedCar.setName(car.getName());
			retrievedCar.setYear(car.getYear());
			
			return carRepository.save(retrievedCar);
		}catch(IOException e) {
			return null;
		}
	}

	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public Car getCarById(Long id) throws CarException {
		if(id == null) { throw new CarException("Id cannot be null"); }
		
		Optional<Car> car = carRepository.findById(id);
		
		if(car.isPresent() == false) { throw new CarException("Car not found"); }
		
		return car.get();
	}

	@Override
	public ResponseEntity<ByteArrayResource> getPhotoDownloadByKey(String photoKey) {
		try {
			return AmazonUtils.Download(properties.getAwsKeyId(), properties.getAwsSecretKey(), properties.getBucketS3Name(), photoKey);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Car getCarByByPhotoKey(String photoKey) {
		return carRepository.findByPhotoKey(photoKey);
	}
	
	private String generatePhotoKey(MultipartFile multipartFile) {
		String alphaNumericString = Utils.randomAlphaNumericString(10);
		return (alphaNumericString + multipartFile.getOriginalFilename()).replace(" ", "");
	}

	
	
}
