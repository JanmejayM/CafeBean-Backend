package com.cafe.service;

import java.util.List;

import com.cafe.entity.Coffee;

public interface CoffeeService {


	public Coffee createcoffee(Coffee coffee);

	public Coffee updateCoffee(Coffee coffee);

	public List<Coffee> getallCoffee();

	public Coffee getCoffeByID(Long coffeeID);
	public void deleteCoffee(long id);


}
