package br.com.supersim.blog.service;

import java.util.List;

import br.com.supersim.blog.DTO.CategoryDTO;
import br.com.supersim.blog.entity.Category;
import br.com.supersim.blog.exception.CategoryException;

public interface CategoryService {
	
	public CategoryDTO save(CategoryDTO category) throws CategoryException;
	
	public void delete(Long categoryId) throws CategoryException;
	
	public CategoryDTO update(CategoryDTO categoryDTO) throws CategoryException;
	
	public List<CategoryDTO> getAllCategories();
	
	public CategoryDTO getCategoryById(Long id);
	
	public Category getCategoryByName(String name);
	

}
