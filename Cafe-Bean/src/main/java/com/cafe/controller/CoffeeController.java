package com.cafe.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.entity.Coffee;
import com.cafe.service.CoffeeService;


@CrossOrigin("*")
@RequestMapping("coffee-rest")
@RestController
public class CoffeeController {
	
	@Autowired
	CoffeeService coffeeservice;
	
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@GetMapping("/fetch")
	public ResponseEntity<List<Coffee>> getCoffee()
	{
		logger.info("Fetching list of Coffee");
		return  new ResponseEntity<List<Coffee>>(coffeeservice.getallCoffee(),HttpStatus.OK);
	}
	@GetMapping("/fetch/{id}")
	public ResponseEntity<Coffee> getCoffeeByID(@PathVariable Long id)
	{
		logger.info("Fetching coffee by id");
		return  new ResponseEntity<Coffee>(coffeeservice.getCoffeByID(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateCoffee")
	public void updateCoffee(@Valid @RequestBody Coffee coffee)
	{
		logger.info("Updating coffee");
		coffeeservice.updateCoffee(coffee);
	}
    
	@PostMapping("/createCoffee")
	public void createCoffee(@Valid @RequestBody Coffee coffee)
	{
		logger.info("Creating coffee");
		coffeeservice.createcoffee(coffee);
	}
	
	@DeleteMapping("/deleteCoffee/{id}")
	public void deleteCoffee(@PathVariable Long id)
	{
		coffeeservice.deleteCoffee(id);
	}
}
