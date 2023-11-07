package com.anarghya.ayurveda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootCategoryServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCategoryServicesApplication.class, args);
	}

}
