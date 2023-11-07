package com.anarghya.customer.ayur.service;

import java.util.List;

import com.anarghya.customer.ayur.entity.Customer;
import com.anarghya.customer.ayur.entity.Login;
import com.anarghya.customer.ayur.exception.UserNotFoundException;
import com.anarghya.customer.ayur.loginresponse.LoginMesage;

public interface CustomerService {
	//method to add customer in to the customer table
	//This method also used for Registration of Customer
	 Customer addNewCustomer(Customer customer);
	
	
	// method to delete the customer details from customer table using id
	Customer deleteCustomer(int customerId) throws UserNotFoundException;
	
	//method to get customer details from the customer table using id
	Customer getCustomer(int customerId) throws UserNotFoundException;
	
	//method to get customer details from the customer table using emailId
	Customer getCustomerbyEmail(String emailId) throws UserNotFoundException;
	
	// method to update the customer details in customer table using id
	Customer updateCustomer(Customer customer,int customerId) throws UserNotFoundException;
	
	//method to get all the customers from the customer table
	List<Customer> getAllCustomers() throws UserNotFoundException;
	
	//method to Login customer
	LoginMesage loginCustomer(Login login);
	
	//method to reset password
	LoginMesage resetPassword(Login login);
	
	//changing password by using otp
	LoginMesage password(Login login);
	
	// This method is useful for frontend profile update
	Customer updateCustomerEmail(Customer model, Long id);


}
