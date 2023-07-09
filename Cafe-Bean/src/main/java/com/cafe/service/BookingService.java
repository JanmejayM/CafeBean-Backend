package com.cafe.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cafe.dto.BookingDto;
import com.cafe.entity.Booking;
import com.cafe.entity.CafeTable;

public interface BookingService {
	public Booking BookTableForUser(BookingDto booking);

	public List<Booking> GetAllTheBookingOfUser(Long id);

	public Set<Long> GetAvailableTables(Date BookingDate,LocalTime starttime);
	public List<Booking>getAllBookings();


}
