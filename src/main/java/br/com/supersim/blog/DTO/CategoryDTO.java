package br.com.supersim.blog.DTO;


import javax.validation.constraints.NotBlank;

import br.com.supersim.blog.entity.Category;

public class CategoryDTO {

	private Long id;
	@NotBlank(message = "Category name is required")
	private String name;
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Category transformToCategory() {
		return new Category(this);
	}
	
}
