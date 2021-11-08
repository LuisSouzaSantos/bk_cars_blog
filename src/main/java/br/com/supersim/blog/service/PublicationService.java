package br.com.supersim.blog.service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import br.com.supersim.blog.DTO.PublicationDTO;
import br.com.supersim.blog.exception.PublicationException;
import br.com.supersim.blog.exception.UserException;

public interface PublicationService {
	
	public PublicationDTO save(PublicationDTO publicationDTO, MultipartFile multipartFile, Principal requestingUser) throws UserException, PublicationException;
	
	public void delete(Long publicationId, Principal requestingUser) throws PublicationException, UserException;
	
	public PublicationDTO update(PublicationDTO publicationDTO, MultipartFile multipartFile, Principal requestingUser) throws UserException, PublicationException;;
	
	public List<PublicationDTO> getAllPublications();
	
	public PublicationDTO getPublicationById(Long id) throws IOException;
	
	public ResponseEntity<ByteArrayResource> getPhotoDownloadByKey(String photoKey);

}
