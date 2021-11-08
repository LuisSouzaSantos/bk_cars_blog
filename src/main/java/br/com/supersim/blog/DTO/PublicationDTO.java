package br.com.supersim.blog.DTO;

import java.util.Calendar;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.supersim.blog.entity.Publication;

public class PublicationDTO {
	
	private Long id;
	@NotBlank(message = "Title is mandatory")
	private String title;
	@NotBlank(message = "Content is mandatory")
	private String content;
	private String photoKey;
	@NotNull
	private CategoryDTO categoryDTO;
	private Calendar calendar;
	@NotNull
	private UserDTO userDTO;

	public PublicationDTO() {
	}
	
	public PublicationDTO(Publication publication) {
		this.id = publication.getId();
		this.title = publication.getTitle();
		this.content = publication.getContent();
		this.photoKey = publication.getPhotoKey();
		this.categoryDTO = new CategoryDTO(publication.getCategory());
		this.calendar = publication.getCalendar();
		this.userDTO = new UserDTO(publication.getUser());
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPhotoKey() {
		return photoKey;
	}

	public void setPhotoKey(String photoKey) {
		this.photoKey = photoKey;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
}
