package com.cafe.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.entity.Customer;
import com.cafe.service.CustomerService;

@CrossOrigin("*")
@RequestMapping("customer-rest")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@PostMapping("/createUser")
	public ResponseEntity<Customer> createUser(@Valid  @RequestBody Customer customer)
	{
		logger.info("Creating a new User");
		return new ResponseEntity<Customer>(customerService.createCustomer(customer),HttpStatus.OK);
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<Customer>getUserById(@PathVariable Long id)
	{
		logger.info("Fetching a user by id");
		return new ResponseEntity<Customer>(customerService.getCustomerByID(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Customer>getUserById(@RequestBody Customer customer)
	{
		logger.info("Updating the User Type");
		return new ResponseEntity<Customer>(customerService.updateCustomer(customer),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Customer>>getAllUser()
	{
		logger.info("Fetching list of all Users");
		return new ResponseEntity<List<Customer>>(customerService.getallCustomer(),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public Customer login(@RequestBody Customer customer)
	{
		return customerService.login(customer);
	}
}
