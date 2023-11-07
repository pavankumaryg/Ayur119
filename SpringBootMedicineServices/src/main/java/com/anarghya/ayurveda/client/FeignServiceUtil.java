package com.anarghya.ayurveda.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//import com.anarghya.ayurveda.model.CategoryModel;

@FeignClient(value = "category", url = "http://localhost:2023")
public interface FeignServiceUtil {

//	@GetMapping("/category")
//	public List<CategoryModel> getAllCategoryModels();
}
