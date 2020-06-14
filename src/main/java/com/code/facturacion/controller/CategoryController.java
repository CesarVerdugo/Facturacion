package com.code.facturacion.controller;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.facturacion.entity.Category;
import com.code.facturacion.repository.CategoryRepository;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public List<Category> getCategories(){
		return categoryRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		return ResponseEntity.ok(categoryRepository.save(category));
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
		categoryRepository.getOne(id);
		
		return ResponseEntity.ok(categoryRepository.save(category));
	}
	
}
