package br.com.supersim.blog.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.supersim.blog.DTO.PublicationDTO;

@Entity
public class Publication {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	
	@JoinColumn(unique = true)
	private String photoKey;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	private Calendar calendar;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Publication() {
		
	}
	
	public Publication(PublicationDTO publicationDTO, String photoKey) {
		this.title = publicationDTO.getTitle();
		this.content = publicationDTO.getContent();
		this.photoKey = photoKey;
		this.category = publicationDTO.getCategoryDTO().transformToCategory();
		this.calendar = Calendar.getInstance();
		this.user = publicationDTO.getUserDTO().transformToUser();
	}
	
	

	public Publication(Long id, PublicationDTO publicationDTO, String photoKey) {
		this.id = id;
		this.title = publicationDTO.getTitle();
		this.content = publicationDTO.getContent();
		this.photoKey = photoKey;
		this.category = publicationDTO.getCategoryDTO().transformToCategory();
		this.calendar = Calendar.getInstance();
		this.user = publicationDTO.getUserDTO().transformToUser();
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
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
