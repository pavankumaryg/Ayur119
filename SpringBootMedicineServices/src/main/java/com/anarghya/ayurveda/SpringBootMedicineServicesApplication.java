package com.anarghya.ayurveda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootMedicineServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMedicineServicesApplication.class, args);
	}

}
