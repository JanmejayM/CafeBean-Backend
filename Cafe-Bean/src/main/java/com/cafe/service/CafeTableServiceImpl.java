package com.cafe.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.dao.CafeTableDao;
import com.cafe.entity.Booking;
import com.cafe.entity.CafeTable;
import com.cafe.entity.Coffee;
import com.cafe.exception.ResourceNotFoundException;

@Service
public class CafeTableServiceImpl implements CafeTableService{
	@Autowired
    private CafeTableDao cafeTableDao;
	
	@Autowired
	BookingService bookingservice;

	@Override
	public CafeTable cafeTableByID(Long cafeTableID) {
		// TODO Auto-generated method stub
	    if(cafeTableDao.findById(cafeTableID).isPresent())
	    {
	    	return cafeTableDao.findById(cafeTableID).get();
	    }
	    throw new ResourceNotFoundException("CafeTable Not Valid");
	}

	@Override
	public CafeTable createcafeTable(CafeTable cafeTable) {
		// TODO Auto-generated method stub
		
			return cafeTableDao.save(cafeTable);
			

}

	
	
	@Override
	public CafeTable updateCafeTable(CafeTable cafeTable) {
		// TODO Auto-generated method stub
		
		if(cafeTableDao.findById(cafeTable.getTableId()).isPresent())
		{
			return cafeTableDao.save(cafeTable);
			
		}
		throw new ResourceNotFoundException("Coffee Not Valid");

		
		
	}

	@Override
	public List<CafeTable> getallCafeTable() {
		// TODO Auto-generated method stub
		return cafeTableDao.findAll();
	}
	
	public List<CafeTable>availableTables(List<Long>arr)
	{
		return cafeTableDao.findAll().stream().filter(i->!arr.contains(i.getTableId())).collect(Collectors.toList());
	}

	@Override
	public void deleteCafeTable(long id) {
		// TODO Auto-generated method stub
		
		cafeTableDao.deleteById(id);
		
	}
}
