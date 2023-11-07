package com.anarghya.aayurvedaapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_module")
public class CustomerModule {

	public Long getCustomerId() {
		return id;
	}

	public void setCustomerId(Long customerId) {
		this.id = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Id
	@Column(name = "customer_Id")
	private Long id;
    private String customerName;
    private String emailId;
    private String mobileNumber;
    private String password; 
}
