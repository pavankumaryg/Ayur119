package com.anarghya.aayurvedaapp.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anarghya.aayurvedaapp.entity.MedicineModule;

@Repository
public interface MedicineModuleRepository extends JpaRepository<MedicineModule, Long>{

//	public List<MedicineModule> findAllById(Integer cartId);
	
	Optional<MedicineModule> findByName(String medicineName);
	
	Optional<MedicineModule> findById(Long medicineId);
	
	Optional<MedicineModule>  findBycartId(Integer cartId);
	
	@Transactional
	  void deleteBycartId(Integer cardId);

}
