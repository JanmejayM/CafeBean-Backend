package com.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entity.Coffee;
import com.cafe.entity.CoffeeItem;

@Repository
public interface CoffeeItemDao extends JpaRepository<CoffeeItem,Long>{

	List<CoffeeItem> findBycustid(Long id);
	
	CoffeeItem findBycustidAndCoffee(long custid,Coffee coffee);


	List<CoffeeItem> findAllBycustid(Long id);



	
}
