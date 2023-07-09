package com.cafe.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.entity.Coffee;
import com.cafe.entity.CoffeeItem;
import com.cafe.service.CoffeeItemService;


@CrossOrigin("*")
@RequestMapping("CoffeeItem-rest")
@RestController
public class CoffeeItemController {
	
	@Autowired
	CoffeeItemService coffeeitemservice;
	
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@GetMapping("/fetch/{id}")
	public List<CoffeeItem>getByUser(@PathVariable Long id)
	{
		logger.info("Getting list of CoffeItem based on user");
		return coffeeitemservice.getByUser(id);
	}
	
	@GetMapping("/getPrice/{id}")
	public Long getByUserPrice(@PathVariable Long id)
	{
		logger.info("Getting price by Id");
		return coffeeitemservice.amountForUserBooking(id);
	}
	
	@PostMapping("/addCoffee/{id}")
	public void addCoffee(@Valid  @PathVariable Long id, @RequestBody Coffee coffee)
	{
		 logger.info("Adding a coffee Item");
		 coffeeitemservice.addCoffee(id, coffee);
	}
	@PostMapping("/updateCoffeeItem")
	public void updateCoffeeItem(@Valid @RequestBody CoffeeItem coffeeitem)
	{
		 logger.info("updating a coffee Item");
		 coffeeitemservice.updateCoffeeItem(coffeeitem);
		 
	}
	@DeleteMapping("/delete/{userid}")
	public void deleteUserCoffeeItem(@PathVariable long userid)
	{
		logger.info("Deleting coffeeItem of user");
		coffeeitemservice.deleteAllCoffeeItemsOfUser(userid);
	}
	
	@DeleteMapping("/deleteCoffeeItem/{id}")
	public void deleteCoffeeItem(@PathVariable long id)
	{
		logger.info("Deleting coffeeItem of id");
		coffeeitemservice.deleteCoffeeItem(id);
	}
}
