package com.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
	
	
	public Customer findByemail(String email);

	public Customer findByEmailAndPassword(String email, String password);

}
