package br.com.supersim.blog.controller;

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

import br.com.supersim.blog.DTO.CategoryDTO;
import br.com.supersim.blog.exception.CategoryException;
import br.com.supersim.blog.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public CategoryDTO save(@RequestBody @Valid CategoryDTO categoryDTO) throws CategoryException {
		return categoryService.save(categoryDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) throws CategoryException {
		categoryService.delete(id);
	}
	
	@PutMapping
	public CategoryDTO update(@RequestBody @Valid CategoryDTO categoryDTO) throws CategoryException {
		return categoryService.update(categoryDTO);
	}
	
	@GetMapping("/all")
	public List<CategoryDTO> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	
}
