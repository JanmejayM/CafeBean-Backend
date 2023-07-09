package com.cafe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="coffeeItem")
public class CoffeeItem {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CoffeeItemId;
	
	@ManyToOne
	private Coffee coffee;
	
	
	
	@Column(name="cust_id")
	@NotNull(message = "Please Enter the CUstomer ID")
	private Long custid;
	
	@Column(name="quantity")
	@NotNull(message = "Please Enter the quantity")
	@Min(value = 1, message = "Quantity should be minimum 1")
	private Integer quantity;


	public CoffeeItem() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CoffeeItem(Long coffeeItemId, Coffee coffee, Long cust_id, Integer quantity) {
		super();
		CoffeeItemId = coffeeItemId;
		this.coffee = coffee;
		this.custid = cust_id;
		this.quantity = quantity;
	}


	public Long getCoffeeItemId() {
		return CoffeeItemId;
	}


	public void setCoffeeItemId(Long coffeeItemId) {
		CoffeeItemId = coffeeItemId;
	}


	public Coffee getCoffee() {
		return coffee;
	}


	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}


	public Long getCust_id() {
		return custid;
	}


	public void setCust_id(Long cust_id) {
		this.custid = cust_id;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	


	
	
	

}
