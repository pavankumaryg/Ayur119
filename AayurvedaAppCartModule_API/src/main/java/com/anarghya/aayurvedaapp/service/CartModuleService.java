package com.anarghya.aayurvedaapp.service;

import java.util.List;

import com.anarghya.aayurvedaapp.entity.CartModuleEntity;
import com.anarghya.aayurvedaapp.entity.MedicineModule;
import com.anarghya.aayurvedaapp.exception.CartNotFoundException;

public interface CartModuleService {
	
	public CartModuleEntity createCart(Long customerId);
	
	public List<CartModuleEntity> showAll();
	
	public CartModuleEntity deleteMedicine(Integer cartId, Long medicineId) throws CartNotFoundException;
	
	public CartModuleEntity deleteMedicines(Integer cartId) throws CartNotFoundException;
	
	public CartModuleEntity addToCart(Long customerId, Long medicineId) throws CartNotFoundException;
	
	public List<MedicineModule> viewMedicines(Integer cartId);
	
	public CartModuleEntity viewCartInfo(Integer cartId) throws CartNotFoundException;
	
	public CartModuleEntity addMedicine(Integer cartId, Long medicineId, Integer quantity) throws CartNotFoundException;

	
	
}
