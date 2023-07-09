package com.cafe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="coffee")

public class Coffee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="coffee_id")
	private Long coffee_id;
	
	@Column(name="coffee_type")
	@NotEmpty(message = "Please Enter the Coffee Type")
	@Length(min = 4, message = "Size of Coffee Type must be greater than or equal to 4 characters")
	private String coffee_type;
	
	@Column(name="coffee_price")
	@NotNull(message = "Please Enter the Price")
	private Long price;

	public Coffee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coffee(Long coffee_id, String coffee_type, Long price) {
		super();
		this.coffee_id = coffee_id;
		this.coffee_type = coffee_type;
		this.price = price;
	}

	public Long getCoffee_id() {
		return coffee_id;
	}

	public void setCoffee_id(Long coffee_id) {
		this.coffee_id = coffee_id;
	}

	public String getCoffee_type() {
		return coffee_type;
	}

	public void setCoffee_type(String coffee_type) {
		this.coffee_type = coffee_type;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	

	
	

}
