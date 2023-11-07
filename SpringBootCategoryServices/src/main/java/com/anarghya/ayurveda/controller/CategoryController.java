package com.anarghya.ayurveda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anarghya.ayurveda.model.Category;
import com.anarghya.ayurveda.services.CategoryServicesImplementation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryServicesImplementation categoryService;

	@GetMapping("/category/{id}")
	public Optional<Category> getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}

	@GetMapping("/category")
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@PostMapping("/category")
	public ResponseEntity<String> addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}

	@PutMapping("/category/{id}")
	public String updateCategoryById(@RequestBody Category category, @PathVariable Long id) {
		return categoryService.updateCategoryById(category, id);
	}

	@DeleteMapping("/category/{id}")
	public String deleteCategoryById(@PathVariable Long id) {
		return categoryService.deleteCategoryById(id);
	}
}
