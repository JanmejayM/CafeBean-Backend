package com.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.entity.CoffeeItem;
import com.cafe.dao.CoffeeItemDao;
import com.cafe.dao.CustomerDao;
import com.cafe.entity.Coffee;
import com.cafe.exception.ResourceNotFoundException;



@Service
public class CoffeeItemServiceImpl implements CoffeeItemService{
	
	@Autowired
	private CoffeeItemDao coffeeitemdao;
	
	@Autowired
	private CustomerDao customerdao;
	
	
	
	public void addCoffee(Long custid,Coffee coffee)
	{
		CoffeeItem c=new CoffeeItem();
		if(customerdao.findById(custid).isPresent())
		{
       
		
		if(coffeeitemdao.findBycustidAndCoffee(custid, coffee)!=null)
		{
			 c=coffeeitemdao.findBycustidAndCoffee(custid, coffee);
			 c.setQuantity(c.getQuantity()+1);
			 coffeeitemdao.save(c);
			 return;
			
		}
		c.setCoffee(coffee);
		c.setCust_id(custid);
		c.setQuantity(1);
		
		coffeeitemdao.save(c);
		return;
		}
		throw new ResourceNotFoundException("Invalid User");
		
	}
	
	
//	public void deleteCoffeItem(Long id )
//	{
//		
//		if(customerdao.findById(id).isPresent())
//		{
//
//		coffeeitemdao.delete(coffeeitemdao.findById(id).get());
//		return;
//		}
//
//		
//	}
	
	public List<CoffeeItem> getByUser(Long id)
	{
		if(customerdao.findById(id).isPresent())
		{
	
		return coffeeitemdao.findBycustid(id);
		
		}
		throw new ResourceNotFoundException("Invalid User");

	}
	
	public Long amountForUserBooking(Long id)
	{

		long finalPrice=coffeeitemdao.findBycustid(id).stream().map(price->price.getQuantity()*price.getCoffee().getPrice()).reduce(0L,(ans, i)->ans+i);
		
		return finalPrice;
		

	}
	
	
	public void deleteAllCoffeeItemsOfUser(Long id)
	{
		List<CoffeeItem>c=coffeeitemdao.findAllBycustid(id);
		
		coffeeitemdao.deleteAll(c);
	}
	
	public void updateCoffeeItem(CoffeeItem c)
	{
		coffeeitemdao.save(c);
	}
	
	public void deleteCoffeeItem(long id)
	{
		coffeeitemdao.deleteById(id);
	}
	

}
