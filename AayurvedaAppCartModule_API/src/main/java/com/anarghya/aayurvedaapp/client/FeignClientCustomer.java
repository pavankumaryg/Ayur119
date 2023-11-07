package com.anarghya.aayurvedaapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.anarghya.aayurvedaapp.entity.CustomerModule;

@FeignClient(value = "CustomerModule-API", url = "http://localhost:9092/api-v1")
public interface FeignClientCustomer {

	@GetMapping("/customers/{customerId}")
	public CustomerModule viewCustomer(@PathVariable Long customerId);

}
