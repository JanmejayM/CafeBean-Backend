package com.cafe.service;

import java.util.List;

import com.cafe.entity.Coffee;
import com.cafe.entity.CoffeeItem;

public interface CoffeeItemService {

	public void addCoffee(Long custid,Coffee coffee);
	
	
	public List<CoffeeItem> getByUser(Long Id);
	public Long amountForUserBooking(Long id);
	
	public void deleteAllCoffeeItemsOfUser(Long id);

	public void updateCoffeeItem(CoffeeItem c);
	public void deleteCoffeeItem(long id);


}
