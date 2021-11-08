package br.com.supersim.blog.serviceImpl;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.supersim.blog.DTO.PublicationDTO;
import br.com.supersim.blog.DTO.UserDTO;
import br.com.supersim.blog.entity.Category;
import br.com.supersim.blog.entity.Properties;
import br.com.supersim.blog.entity.Publication;
import br.com.supersim.blog.exception.PublicationException;
import br.com.supersim.blog.exception.UserException;
import br.com.supersim.blog.repository.PublicationRepository;
import br.com.supersim.blog.service.CategoryService;
import br.com.supersim.blog.service.PublicationService;
import br.com.supersim.blog.service.UserService;
import br.com.supersim.blog.utils.AmazonUtils;
import br.com.supersim.blog.utils.Utils;

@Service
public class PublicationServiceImpl implements PublicationService {
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private Properties properties;

	@Override
	public PublicationDTO save(PublicationDTO publicationDTO, MultipartFile multipartFile, Principal requestingUser) throws UserException, PublicationException {
		
		if(requestingUser.getName() == null) { throw new PublicationException("Invalid user publication");}
		
		UserDTO userPublication = userService.getUserDTOByEmail(requestingUser.getName());
		
		if(userPublication == null) { throw new PublicationException("Invalid user publication");}
		
		Category category = categoryService.getCategoryByName(publicationDTO.getCategoryDTO().getName());
		
		if(category == null) { throw new PublicationException("Invalid category publication");}
		
		try {
			
			String photoKey = null;
			
			while(true) {
				photoKey = generatePhotoKey(multipartFile);
				Publication publication = publicationRepository.findByPhotoKey(photoKey);
				if(publication == null) {
					break;
				}
			}
			
			publicationDTO.setUserDTO(userPublication);
			AmazonUtils.Upload(properties.getAwsKeyId(), properties.getAwsSecretKey(), properties.getBucketS3Name(), multipartFile, photoKey);
			Publication publication = new Publication(publicationDTO, photoKey);
			return new PublicationDTO(publicationRepository.save(publication));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public void delete(Long publicationId, Principal requestingUser) throws PublicationException, UserException {
	
		if(requestingUser.getName() == null) { throw new PublicationException("Invalid user publication");}
		
		UserDTO userPublication = userService.getUserDTOByEmail(requestingUser.getName());
		
		Optional<Publication> publication = publicationRepository.findById(publicationId);
		
		if(publication.isPresent() == false) { return; }
		
		if(publication.get().getUser().getId().equals(userPublication.getId()) == false) {
			throw new PublicationException("User is not allowed to delete this publication");
		}
		
		publicationRepository.delete(publication.get());
		
	}

	@Override
	public PublicationDTO update(PublicationDTO publicationDTO, MultipartFile multipartFile, Principal requestingUser) throws UserException, PublicationException  {
		
		if(publicationDTO.getId() == null) {throw new PublicationException("Invalid publication invalid"); }
		
		Optional<Publication> optionalPublication = publicationRepository.findById(publicationDTO.getId());
		
		if(optionalPublication.isPresent() == false) {throw new PublicationException("Invalid publication invalid"); }
		
		if(requestingUser.getName() == null) { throw new PublicationException("Invalid user publication");}
		
		UserDTO userPublication = userService.getUserDTOByEmail(requestingUser.getName());
		
		if(userPublication == null) { throw new PublicationException("Invalid user publication");}
		
		Category category = categoryService.getCategoryByName(publicationDTO.getCategoryDTO().getName());
		
		if(category == null) { throw new PublicationException("Invalid category publication");}
		
		try {
			
			String photoKey = null;
			Publication publication = optionalPublication.get();
			if(multipartFile != null) {
				while(true) {
					photoKey = generatePhotoKey(multipartFile);
					Publication publicationByPhotoKey = publicationRepository.findByPhotoKey(photoKey);
					if(publicationByPhotoKey == null) {
						break;
					}
				}
				publication.setPhotoKey(photoKey);
				AmazonUtils.Upload(properties.getAwsKeyId(), properties.getAwsSecretKey(), properties.getBucketS3Name(), multipartFile, photoKey);
			}
			
			publication.setTitle(publicationDTO.getTitle());
			publication.setContent(publicationDTO.getContent());
			publication.setCategory(category);
			publication.setCalendar(Calendar.getInstance());
			return new PublicationDTO(publicationRepository.save(publication));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<PublicationDTO> getAllPublications() {
		return publicationRepository.publicationOrdeyByDesc().stream().map(PublicationDTO::new).collect(Collectors.toList());
	}
	
	private String generatePhotoKey(MultipartFile multipartFile) {
		String alphaNumericString = Utils.randomAlphaNumericString(10);
		return (alphaNumericString + multipartFile.getOriginalFilename()).replace(" ", "");
	}

	@Override
	public PublicationDTO getPublicationById(Long id) throws IOException {
		
		Optional<Publication> publication = publicationRepository.findById(id);
		
		if(publication.isPresent() == false) { return null; }
		
		PublicationDTO publicationDTO = new PublicationDTO(publication.get());
		return publicationDTO;
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
	
	



}
