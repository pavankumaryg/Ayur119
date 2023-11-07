package com.anarghya.customer.ayur.service;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.anarghya.customer.ayur.entity.Customer;
import com.anarghya.customer.ayur.entity.Login;
import com.anarghya.customer.ayur.exception.UserNotFoundException;
import com.anarghya.customer.ayur.loginresponse.LoginMesage;
import com.anarghya.customer.ayur.repo.CustomerRepository;

/****************************
 * @Service: It is used with classes that provide some business functionalities.
 ****************************/
@Service
public class CustomerServiceImplee implements CustomerService{
	
	/****************************
	 * @Autowired: It allows Spring to resolve and inject collaborating beans into
	 *             our bean.
	 ****************************/
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private JavaMailSender mailService;
	
	// method to find the details of the customer using customer Id---------------------------

		@Override
		public Customer getCustomer(int customerId) throws UserNotFoundException{
			Optional<Customer> findById = customerRepository.findById((long) customerId);

			return findById.orElseThrow(() -> new UserNotFoundException("There are no customer having id:" + customerId));
		}

		// method to get all the customers--------------------------------

		@Override
		public List<Customer> getAllCustomers() throws UserNotFoundException{

			if (customerRepository.findAll().isEmpty())
				throw new UserNotFoundException("There are no records");

			return customerRepository.findAll();

		}

		// method to delete the customer using customer Id-------------------------------------

		@Override
		public Customer deleteCustomer(int customerId) throws UserNotFoundException{

			Optional<Customer> findById = customerRepository. findById((long) customerId);
			findById.orElseThrow(() -> new UserNotFoundException("There are no customer having id:" + customerId));

			customerRepository.deleteById((long) customerId);

			return findById.get();
		}
		
  
		 // method to add new customer----------------------------------------
		
		@Override
		public Customer addNewCustomer(Customer customer) {
			String password = customer.getPassword();
			 java.util.Base64.Encoder  encoder= Base64.getEncoder();
			String encodepassword=encoder.encodeToString(password.getBytes());
			customer.setPassword(encodepassword);
			customerRepository.save(customer);
			return customer;
		}

		
		// method to update the customer using customer Id---------------------------------------
		@Override
		public Customer updateCustomer(Customer customer,int customerId) throws UserNotFoundException {
			Optional<Customer> customerOptional = customerRepository.findById((long) customerId);
			if(customerOptional.isPresent()) {
				Customer customerToBeUpdated = customerOptional.get();
				System.out.println("---> customerFromDB:"+customerToBeUpdated);
				customerToBeUpdated.setCustomerId(customer.getCustomerId());
				customerToBeUpdated.setCustomerName(customer.getCustomerName());
				customerToBeUpdated.setEmailId(customer.getEmailId());
				customerToBeUpdated.setMobileNumber(customer.getMobileNumber());
				Encoder  encoder= Base64.getEncoder();
				String encodepassword=encoder.encodeToString(customer.getPassword().getBytes());
				customerToBeUpdated.setPassword(encodepassword);
				customerToBeUpdated.setAddress(customer.getAddress());
				customerRepository.save(customerToBeUpdated);
				return customerToBeUpdated;
			}
			return customerOptional.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		}
		
		// method to login using the customer table details--------------------------------
		
		public LoginMesage  loginCustomer(Login login) {
			  
	        String mail = login.getEmailId();
	        String number=login.getField();
//	        CustomerModel employee1 = reop.findByEmailId(loginDAO.getEmailId());
	        Customer customer = customerRepository.findOneByEmailIdOrMobileNumber(number, number);
	        if (customer != null) {
	            String password = login.getPassword();
	            
	            java.util.Base64.Encoder  encoder= Base64.getEncoder();
	    		String getpassword=encoder.encodeToString(password.getBytes());
	            
	            String encodedPassword = customer.getPassword();
	          //  Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
	            if (getpassword.equals(encodedPassword)) {
//	                Optional<CustomerModel> customer = reop.findOneByEmailIdAndPassword(loginDAO.getEmailId(), encodedPassword);
	            	  Optional<Customer> customer1 = customerRepository.findOneByEmailIdOrMobileNumberAndPassword(number, number, encodedPassword);
	                if (customer1.isPresent()) {
	                    return new LoginMesage("Login Success", true);
	                } else {
	                    return new LoginMesage("Login Failed", false);
	                }
	            } else {
	                return new LoginMesage("password Not Match", false);
	            }
	        }else {
	            return new LoginMesage("Email not exists", false);
	        }
	    }
		  
		 		    		 
		 //method to reset password if user forgets their old password----------------------------------
		 
		 
		 String otp;
		 Long id;
		 public LoginMesage resetPassword(Login login) {
			 
			  
			  Customer mail1 = customerRepository.findByEmailId(login.getEmailId());
			  
			   if(mail1 != null) {
				   String mail = mail1.getEmailId();
				   String m =login.getEmailId();
			  if(mail.equals(m)) {
				  
				  SimpleMailMessage message = new SimpleMailMessage();
					message.setTo(mail);
					message.setSubject("Reset you are password OTP ");
					Random random =  new Random();
					 otp =(Integer.toString( random.nextInt(999999)));
					  String body= "HELLO......!\n You are Requested for Reset Password\n Please enter Your one time Otp...."+otp;
					message.setText(body);
				   mailService.send(message);
					   id = mail1.getCustomerId();
				   return new LoginMesage("Otp Send to mail check it",true);
			  }
			  else {
				  return new LoginMesage("email not matched ",false);
			  }
			   }
			   else {
				   return new LoginMesage("mail is not exists",false);
			   }
		  }
	  
		 
		 
		 //method to change password by using otp------------------------------------------------
			
		  public LoginMesage password(Login login) {
			  
			  if(login.getOtp().equals(otp)) {
				  Customer mail1 = customerRepository.findByEmailId(login.getEmailId());
				  
				  
				  Optional<Customer> model = customerRepository.findById(id);
				  Customer models = model.get();
				  String mail2 =models.getEmailId();	
				  
				  Encoder  encoder= Base64.getEncoder();
					String encodepassword=encoder.encodeToString(login.getPassword().getBytes());
				  models.setPassword(encodepassword);
				  customerRepository.save(models);
				   
				   
					  SimpleMailMessage message = new SimpleMailMessage();
						message.setTo(mail2);
						message.setSubject("Reset you are  new password  ");
						Random random =  new Random();
						int atIndex = mail2.indexOf('@');
						 String username = mail2.substring(0, atIndex);
						  String body= "Welcome...... " + username  +"\nyou are new  password is........."+login.getPassword()+" \nplease be login with your new password";
						message.setText(body);
					   mailService.send(message);
					   
				  return  new LoginMesage("Password reset Success full",true);
			  }
			  else {
				  return new LoginMesage("OTP is incorrect  ",false);
			  }

}

		// method to find the details of the customer using emailId---------------------------
		  
		@Override
		public Customer getCustomerbyEmail(String emailId) throws UserNotFoundException {
			
			Customer findByEmailId = customerRepository.findByEmailId(emailId);

			return findByEmailId;
		}
		
		
		// method to find the details of the customer using emailId---------------------------
		
		public Customer getCustomerbyEmailId(String emailId) {
			Customer findByEmailId =customerRepository.findOneByEmailIdOrMobileNumber(emailId, emailId);
			return findByEmailId;
		}
		
		
		
		
		// method to update only required variables in the frontend customer profile
		// This method is applicable for frontend customer update
		
		public Customer updateCustomerEmail(Customer model, Long id) {
			// TODO Auto-generated method stub
			Optional<Customer>  optional = customerRepository.findById(id);
			Customer models = optional.get();
			  models.setCustomerId(model.getCustomerId());;
			  models.setCustomerName(model.getCustomerName());
//			  models.setEmailId(model.getEmailId());
			  models.setMobileNumber(model.getMobileNumber());
			  models.setAddress(model.getAddress());
//			  models.setPassword(model.getPassword());
			  
			
			return customerRepository.save(models);

 }
}
