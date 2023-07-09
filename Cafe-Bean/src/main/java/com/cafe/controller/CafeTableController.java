package com.cafe.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.text.SimpleDateFormat;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cafe.entity.Booking;
import com.cafe.entity.CafeTable;
import com.cafe.service.CafeTableService;


@CrossOrigin("*")
@RequestMapping("cafeTable-rest")
@RestController
public class CafeTableController {
	@Autowired
	CafeTableService cafeTableservice;
	
	@Autowired
	RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@GetMapping("/fetch")
	public ResponseEntity<List<CafeTable>> getCafeTable()
	{
		logger.info("Fetching List of CafeTables");
		return  new ResponseEntity<List<CafeTable>>(cafeTableservice.getallCafeTable(),HttpStatus.OK);
	}
	@GetMapping("/fetch/{id}")
	public ResponseEntity<CafeTable> getCafeTableByID(@PathVariable Long id)
	{
		logger.info("Fetching a particular Cafetable according to id");
		return  new ResponseEntity<CafeTable>(cafeTableservice.cafeTableByID(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateCafeTable")
	public void updateCafeTable(@Valid @RequestBody CafeTable cafeTable)
	{
		logger.info("Updating CafeTables");
		cafeTableservice.updateCafeTable(cafeTable);
	}
	@PostMapping("/createCafeTable")
	public void createCafeTable(@Valid @RequestBody CafeTable cafeTable)
	{
		logger.info("Creating cafeTables");
		cafeTableservice.createcafeTable(cafeTable);
	}
	
	@DeleteMapping("/deleteCafeTable/{id}")
	public void deleteCafeTable(@PathVariable Long id)
	{
		cafeTableservice.deleteCafeTable(id);
	}
	
	@GetMapping("/availableTable")
	public List<CafeTable> availableCafeTable(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  Date date,@RequestParam LocalTime time )
	{
		
		
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Specify the desired date format
		String formattedDate = formatter.format(date); // Format the date using the formatter

		
        LocalDate currentDate =  LocalDate.now(); // Current date

		if(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(currentDate))
		{
			throw new RuntimeException("Invalid date");
		}
		
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8080/Booking-rest/getBookings")
                .queryParam("date", formattedDate )
                .queryParam("starttime", time);
        
        String url = builder.toUriString();
        ResponseEntity<Long[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Long[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Long[] objects = response.getBody();
           
            return cafeTableservice.availableTables(Arrays.asList(objects));
            
        } else {
            System.out.println("Request failed with status code: " + response.getStatusCodeValue());
            return null;
        }

	}
}
