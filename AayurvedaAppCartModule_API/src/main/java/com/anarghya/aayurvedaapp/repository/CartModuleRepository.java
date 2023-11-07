package com.anarghya.aayurvedaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anarghya.aayurvedaapp.entity.CartModuleEntity;

@Repository
public interface CartModuleRepository extends JpaRepository<CartModuleEntity, Integer> {

//	public CartModuleEntity deleteByCartIdAndMedcinineId(Integer cartId, Integer medicineId);
	
//	public List<CartModuleEntity> findByMedicines(List<MedicineModule> medicines);

	public CartModuleEntity findByCustomerId(Long customerId);
	

}
