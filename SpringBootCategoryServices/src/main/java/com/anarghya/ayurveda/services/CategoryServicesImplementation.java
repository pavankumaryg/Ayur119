package com.anarghya.ayurveda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anarghya.ayurveda.model.Category;
import com.anarghya.ayurveda.repository.CategoryRepository;

@Service
public class CategoryServicesImplementation implements CategoryServices {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Optional<Category> getCategoryById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public ResponseEntity<String> addCategory(Category category) {

		if (categoryRepository.findByName(category.getName()).isPresent()) {
			return ResponseEntity.badRequest().body("Category is already present");
		}
		categoryRepository.save(category);
		return ResponseEntity.ok("Category added successfully");
	}

	@Override
	public String updateCategoryById(Category category, Long id) {
		category.setId(id);
		categoryRepository.save(category);
		return "category updated successfully";
	}

	@Override
	public String deleteCategoryById(Long id) {
		categoryRepository.deleteById(id);
		return "deleted successfully";
	}

}
