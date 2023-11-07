package com.anarghya.ayurveda.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.anarghya.ayurveda.model.Medicine;

public interface MedicineServices {

	public List<Medicine> getAllMedicines();

	public Medicine getMedicineById(Long id);

	public ResponseEntity<String> addMedicine(Medicine medicine);

	public Medicine updateMedicine(Long id, Medicine updatedMedicine);

	public void deleteMedicine(Long id);
	
}
