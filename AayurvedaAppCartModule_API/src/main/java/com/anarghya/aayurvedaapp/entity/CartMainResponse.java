 package com.anarghya.aayurvedaapp.entity;

import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
public class CartMainResponse {
	@Id
	private Integer cartId;
	private Integer quantity;
	private Double cost;

//	private CustomerModule customer;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	

//	public CustomerModule getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(CustomerModule customer) {
//		this.customer = customer;
//	}

	public MedicineModule getMedicines() {
		return medicines;
	}

	public void setMedicines(MedicineModule medicines) {
		this.medicines = medicines;
	}

	private MedicineModule medicines;
}
