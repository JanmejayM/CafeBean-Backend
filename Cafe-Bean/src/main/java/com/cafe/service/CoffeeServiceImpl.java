package com.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.dao.CoffeeDao;
import com.cafe.dao.CustomerDao;
import com.cafe.entity.Coffee;
import com.cafe.entity.Customer;
import com.cafe.exception.ResourceNotFoundException;


@Service
public class CoffeeServiceImpl implements CoffeeService{
	

	@Autowired
    private CoffeeDao coffeeDao;

	@Override
	public Coffee getCoffeByID(Long coffeeID) {
		// TODO Auto-generated method stub
	    if(coffeeDao.findById(coffeeID).isPresent())
	    {
	    	return coffeeDao.findById(coffeeID).get();
	    }
	    throw new ResourceNotFoundException("Coffee Not Valid");
	}

	@Override
	public Coffee createcoffee(Coffee coffee) {
		// TODO Auto-generated method stub
		
			return coffeeDao.save(coffee);
			
		
		
		
		
		
	}

	@Override
	public Coffee updateCoffee(Coffee coffee) {
		// TODO Auto-generated method stub
		
		if(coffeeDao.findById(coffee.getCoffee_id()).isPresent())
		{
			return coffeeDao.save(coffee);
			
		}
		throw new ResourceNotFoundException("Coffee Not Valid");

		
		
	}

	@Override
	public List<Coffee> getallCoffee() {
		// TODO Auto-generated method stub
		return coffeeDao.findAll();
	}
	
	public void deleteCoffee(long id)
	{
		
		coffeeDao.deleteById(id);
	}

}
