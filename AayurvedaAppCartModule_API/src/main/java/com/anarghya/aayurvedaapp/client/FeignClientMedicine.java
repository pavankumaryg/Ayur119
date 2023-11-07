package com.anarghya.aayurvedaapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.anarghya.aayurvedaapp.entity.MedicineModule;

@FeignClient(value = "MedicineModule-API", url = "http://localhost:2024/api")
public interface FeignClientMedicine {

	@GetMapping("/medicines/{id}")
	public ResponseEntity<MedicineModule> getMedicineById(@PathVariable Long id);
}
