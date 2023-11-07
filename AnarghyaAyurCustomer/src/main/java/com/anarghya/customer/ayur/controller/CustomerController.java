package com.anarghya.customer.ayur.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anarghya.customer.ayur.entity.Customer;
import com.anarghya.customer.ayur.entity.Login;
import com.anarghya.customer.ayur.exception.UserNotFoundException;
import com.anarghya.customer.ayur.loginresponse.LoginMesage;
import com.anarghya.customer.ayur.service.CustomerService;
import com.anarghya.customer.ayur.service.CustomerServiceImplee;

/****************************
 * @CrossOrigin: enables cross-origin resource sharing only for this specific
 *               method
 ****************************/
@CrossOrigin("http://localhost:3000")

/****************************
* @RestController: to simplify the creation of RESTful web services
****************************/
@RestController

/****************************
 * @RequestMapping: which is used to map HTTP requests to handler methods of MVC
 *                  and REST controllers
 ****************************/
@RequestMapping("/api-v1")
public class CustomerController {
	
	/****************************
	 * @Autowired: It allows Spring to resolve and inject collaborating beans into
	 *             our bean.
	 ****************************/
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerServiceImplee service;

	/****************************
	 * Method: addNewCustomers Description: It is used to add into the customers table
	 * 
	 * @returns customers It returns String type message
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type. 
	 * @RequestBody: It used to maps the HttpRequest body to a transfer or domain
	 *               object, enabling automatic deserialization of the inbound
	 *               HttpRequest body onto a Java object.
	 *               
	 * Created By-naveen 
	 * 
	 ****************************/

	@PostMapping("/customers")
	public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer) {
		customerService.addNewCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	}

// =========================================================================================================================================	
	
	/****************************
	 * Method: Update customers 
	 * Description: It is used to update customers in customers table
	 * @returns customers It returns String type message 
	 * @PutMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-naveen
	 * 
	 ****************************/	

	@PutMapping({ "/customers/{customerId}" })
	public ResponseEntity<Customer> updateCustomer(@PathVariable int customerId, @RequestBody Customer customer)
			throws UserNotFoundException {
		System.out.println("CustomerID:"+customerId);
		System.out.println("Customer:"+customer);
		//System.out.println(customer.getRole()+"<===");
		customerService.updateCustomer(customer, customerId);
		return new ResponseEntity<Customer>(customer, HttpStatus.ACCEPTED);
	}

// ========================================================================================================================================		
	
	/****************************
	 * Method: viewAllCustomer
	 * Description: It is used to get All Customer items in Customer table
	 * @returns Customer It returns Customer  with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By: naveen
	 * 
	 ****************************/
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewAllCustomers() throws UserNotFoundException {
		return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
	}
	
// ========================================================================================================================================
	
	/****************************
	 * Method: viewCustomer by Id
	 * Description: It is used to get Customer items  by specifying Id in Customer table
	 * @returns Customer It returns Customer  with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By: naveen
	 * 
	 ****************************/
	
	@GetMapping("/customers/{customerId}")
	public Customer viewCustomer(@PathVariable Integer customerId) throws UserNotFoundException {
		return customerService.getCustomer(customerId);
	}
	
	
	// ========================================================================================================================================
	
		/****************************
		 * Method: viewCustomer by emailId
		 * Description: It is used to get Customer items  by specifying emailId in Customer table
		 * @returns Customer It returns Customer  with details
		 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
		 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
		 * Created By: naveen
		 * 
		 ****************************/
		
		@GetMapping("/email/{emailId}")
		public Customer viewCustomerbyEmail(@PathVariable String emailId) throws UserNotFoundException {
			return customerService.getCustomerbyEmail(emailId);
		}

	/****************************
	 * Method: deleteCustomer
	 * Description: It is used to remove the items in the customer table
	 * @returns customer It returns String type message 
	 * @DeleteMapping: It is used to handle the HTTP Delete requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By: naveen
	 * 
	 ****************************/
	
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Customer> deleteById(@PathVariable Integer customerId) throws UserNotFoundException {

		return new ResponseEntity<Customer>(customerService.deleteCustomer(customerId), HttpStatus.OK);
	}
	
	/****************************
	 * Method: loginCustomer
	 * Description: It is used to login and redirected to Respected Location if the details in customer table are true
	 * @returns customer It returns String type message 
	 *@PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By: naveen
	 * 
	 ****************************/
	
	
	@PostMapping("login")
	public LoginMesage login(@RequestBody Login login) {
	LoginMesage loginResponse = service.loginCustomer(login);
		return loginResponse;
	}
	
	/****************************
	 * Method: resetPassword
	 * Description: It is used to reset the password if the customer forgets his old password
	 * @returns customer It returns String type message 
	 *@PutMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By: naveen
	 * 
	 ****************************/

	@PutMapping("/reset")
	public LoginMesage resetPassword(@RequestBody Login login ) {
		LoginMesage msg = service.resetPassword(login);
		return msg;
	}
	
	
	/****************************
	 * Method: newPassword
	 * Description: It is used to change the old password to new password by using entering otp
	 * @returns customer It returns String type message 
	 *@PutMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By: naveen
	 * 
	 ****************************/
	
	
	@PutMapping("/password")
	public LoginMesage password(@RequestBody Login login) {
		LoginMesage msg = service.password(login);
		return msg;
		
	}
	
	
	/****************************
	 * Method: viewCustomerbyEmailId
	 * Description: It is used to view the details of customer based on emailId
	 * @returns customer It returns String type message 
	 *@GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By: naveen
	 * 
	 ****************************/
	
	
	@GetMapping("/emailId/{emailId}")
	public Customer viewCustomerbyEmailId(@PathVariable String emailId)  {
		return service.getCustomerbyEmailId(emailId);
	}
	
	
	
	
	
	/****************************
	 * Method: updateCustomerEmail
	 * Description: This update method is only applicable for updating the customer Information in the frontend customer dashboard profile
	 * @returns customer It returns String type message 
	 *@PutMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By: naveen
	 * 
	 ****************************/
	
	
	@PutMapping("/updateEmail/{id}")
	public Customer updateCustomerEmail(@RequestBody Customer model,@PathVariable   Long id) {
		  return   service.updateCustomerEmail(model, id); 
		
		
	}
	

}
