package com.anarghya.customer.ayur.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anarghya.customer.ayur.entity.Customer;


/*
* @Repository: It is used to indicate that the class provides the mechanism
* for storage, retrieval, search, update and delete operation on objects.
*/

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	  Customer findByEmailId(String emailId);
	  
	  Customer findOneByEmailIdOrMobileNumber(String emailId, String mobileNumber);

	   Optional<Customer> findOneByEmailIdOrMobileNumberAndPassword(String emailId, String mobileNumber, String password);


}
