package com.cafe.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cafe.entity.Booking;
import com.cafe.entity.CafeTable;

public interface CafeTableService {

	

	public CafeTable cafeTableByID(Long cafeTableID);

	public CafeTable createcafeTable(CafeTable cafeTable);


	public List<CafeTable> getallCafeTable();

	public CafeTable updateCafeTable(CafeTable cafeTable);
	
	public List<CafeTable>availableTables(List<Long>arr);
	
	public void deleteCafeTable(long id);

	
	
	

}
