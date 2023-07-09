package com.cafe.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.dao.CustomerDao;
import com.cafe.entity.Customer;
import com.cafe.exception.ResourceNotFoundException;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	
	
	@Autowired
    private CustomerDao customerDao;

	@Override
	public Customer getCustomerByID(Long customerID) {
		// TODO Auto-generated method stub
	    if(customerDao.findById(customerID).isPresent())
	    {
	    	return customerDao.findById(customerID).get();
	    }
	    throw new ResourceNotFoundException("User Not Valid");
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		//if(customerDao.findByemail(customer.))
		
		Customer cust=customerDao.findByemail(customer.getEmail());
		
		if(cust==null) {
			return customerDao.save(customer);
			
		}
		
		throw new ResourceNotFoundException("User Already Exists");
		
		
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		if(customerDao.findById(customer.getCust_id()).isPresent())
		{
			return customerDao.save(customer);
			
		}
		throw new ResourceNotFoundException("User Not Valid");

		
		
	}

	@Override
	public List<Customer> getallCustomer() {
		// TODO Auto-generated method stub
		return customerDao.findAll();
	}
	
	public Customer login(Customer c)
	{
		if(customerDao.findByEmailAndPassword(c.getEmail(),c.getPassword())!=null)
		{
			return customerDao.findByEmailAndPassword(c.getEmail(),c.getPassword());
		}
		throw new ResourceNotFoundException("User Not Registered");

	}

//	@Override
//	public void deleteCustomer(Long customerID) {
//		// TODO Auto-generated method stub
//		
//	}
//    
   
	

}
