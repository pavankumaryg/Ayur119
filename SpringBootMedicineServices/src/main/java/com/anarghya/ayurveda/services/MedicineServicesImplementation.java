package com.anarghya.ayurveda.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import com.anarghya.ayurveda.model.Category;
import com.anarghya.ayurveda.model.Medicine;
//import com.anarghya.ayurveda.repository.CategoryRepository;
import com.anarghya.ayurveda.repository.MedicineRepository;
/****************************
 * @Service: It is used with classes that provide some business functionalities.
 ****************************/
@Service
public class MedicineServicesImplementation implements MedicineServices {

	/****************************
	 * @Autowired: It allows Spring to resolve and inject collaborating beans into
	 *             our bean.
	 ****************************/
	@Autowired
	private MedicineRepository medicineRepository;
	
//	@Autowired
//	private CategoryRepository categoryRepository;

	@Override
	public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

	@Override
	public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id).orElse(null);
    }
	@Override
	 public ResponseEntity<String> addMedicine( Medicine medicine) {

		if(medicineRepository.findByName(medicine.getName()).isPresent()) {
			return ResponseEntity.badRequest().body("Medicine is already present.");
		}
		medicineRepository.save(medicine);
        return ResponseEntity.ok("Medicine added successfully");
    }
	@Override
	public Medicine updateMedicine(Long id, Medicine updatedMedicine) {
		updatedMedicine.setId(id);
		return medicineRepository.save(updatedMedicine);
	}

	@Override
	 public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

//	public List<Medicine> getMedicineByCategory(Long  id ) {
//		List<Medicine> matchingMedicines = new ArrayList<>();
//		Optional<Category> categoryOptional=categoryRepository.findById(id);
//		List<Medicine> medicines=medicineRepository.findAll();
//		Category category=categoryOptional.get();
//		for(Medicine medicine: medicines) {
//			if(category.getName().equals(medicine.getCategory())) {
//				matchingMedicines.add(medicine);
//			}
//		}
//
//        return matchingMedicines;
//	}

}
