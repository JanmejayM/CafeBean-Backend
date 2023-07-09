package com.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entity.CafeTable;

@Repository
public interface CafeTableDao extends JpaRepository<CafeTable, Long>{

}
