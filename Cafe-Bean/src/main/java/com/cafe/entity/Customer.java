package com.cafe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="customer")
public class Customer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cust_id;
	
	@Column(name="username")
	@NotEmpty(message = "Please Enter the UserName")
	@Length(min = 5, message = "UserName Length Should consist of minimum 5 characters")
	private String username;
	
	@Column(name="password")
	@NotEmpty
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$", message = "password must be min 8 and max 16 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	@Length(min = 8, max = 16)
	private String password;
	
	@Column(name="email")
	@Email(message = "Please Enter the Valid Email")
	private String email;
	
	@Column(name="phone")
	@Length(min = 10)
	@NotEmpty(message = "Mobile Number should be of minimum 10 digits")
	private String phone;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long cust_id, String username, String password, String email, String phone) {
		super();
		this.cust_id = cust_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
	
	

}
