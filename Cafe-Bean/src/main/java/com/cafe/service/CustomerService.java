package com.cafe.service;

import java.util.List;

import com.cafe.entity.Customer;

public interface CustomerService {
	public Customer getCustomerByID(Long customerID);

	public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public List<Customer> getallCustomer();
	public Customer login(Customer c);

    //void deleteCustomer(Long customerID);
}
