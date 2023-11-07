package com.anarghya.ayurveda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.anarghya.ayurveda.model.Category;

public interface CategoryServices {

	public Optional<Category> getCategoryById(Long id);

	public List<Category> getAllCategories();

	public ResponseEntity<String> addCategory(Category category);

	public String updateCategoryById(Category category, Long id);

	public String deleteCategoryById(Long id);

}
