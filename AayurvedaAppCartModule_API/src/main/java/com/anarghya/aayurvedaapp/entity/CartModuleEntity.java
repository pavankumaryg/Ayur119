package com.anarghya.aayurvedaapp.entity;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name = "cart_modulue")
public class CartModuleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_Id")
	private Integer id;
	@Column(name="total_quantity")
	//@ColumnDefault(value ="1")
	private Integer totalquantity;
	@Column(name="total_cost")
	private Double totalcost;

	public Integer getCartId() {
		return id;
	}

	public void setCartId(Integer cartId) {
		this.id = cartId;
	}

	public Integer getQuantity() {
		return totalquantity;
	}

	public void setQuantity(Integer quantity) {
		this.totalquantity = quantity;
	}

	public Double getCost() {
		return totalcost;
	}

	public void setCost(Double cost) {
		this.totalcost = cost;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_Id")
	private CustomerModule customer;
	
//	@JoinColumn(name = "customer_Id", foreignKey = @ForeignKey(name = "customer_Id"))
//	@Column(name = "customer_Id")
//	private Integer customerId;


	

	public List<MedicineModule> getMedicines() {
		return medicines;
	}

	public CustomerModule getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModule customer) {
		this.customer = customer;
	}

	public void setMedicines(List<MedicineModule> medicines) {
		this.medicines = medicines;
	}
	
	@OneToMany(mappedBy = "cart" )
	@JsonSerialize
	private List<MedicineModule> medicines;
}
