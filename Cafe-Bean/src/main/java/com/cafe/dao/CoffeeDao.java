package com.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entity.Coffee;

@Repository
public interface CoffeeDao extends JpaRepository<Coffee, Long>{

}
