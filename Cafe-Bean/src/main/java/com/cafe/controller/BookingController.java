package com.cafe.controller;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.dto.BookingDto;
import com.cafe.entity.Booking;
import com.cafe.entity.CafeTable;
import com.cafe.service.BookingService;


@CrossOrigin("*")
@RequestMapping("Booking-rest")
@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingservice;
	
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@PostMapping("/addBooking")
	public Booking addBooking( @RequestBody BookingDto booking)
	{
		logger.info("Creating a new Booking");
		return bookingservice.BookTableForUser(booking);
	}
	@GetMapping("/fetch/{id}")
	public List<Booking> getAllBookingsOfUser(@PathVariable long id)
	{
		logger.info("Retrieving List of Bookings of a User");
		return bookingservice.GetAllTheBookingOfUser(id);
	}
	
	@GetMapping("/fetch")
	public List<Booking> getAllBookings()
	{
		logger.info("Retrieving List of All Bookings of a User");
		return bookingservice.getAllBookings();
	}
	
	
	@GetMapping("/getBookings")
	public Set<Long> getAllBookingsOfUser(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  Date date,@RequestParam LocalTime starttime)
	{
		logger.info("Retrieving List of Bookings of a User");
		return bookingservice.GetAvailableTables(date, starttime);
	}
	
	
}
