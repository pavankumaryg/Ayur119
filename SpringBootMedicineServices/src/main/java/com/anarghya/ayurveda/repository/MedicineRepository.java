package com.anarghya.ayurveda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anarghya.ayurveda.model.Medicine;

/*
* @Repository: It is used to indicate that the class provides the mechanism
* for storage, retrieval, search, update and delete operation on objects.
*/
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	Optional<Medicine> findByName(String name);
}
