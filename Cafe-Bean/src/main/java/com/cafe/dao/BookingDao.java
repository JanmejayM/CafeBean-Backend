package com.cafe.dao;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafe.entity.Booking;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long>{

	List<Booking> findBycustid(Long id);

	
	List<Booking> findByBookingdateAndStartTimeField(Date date,LocalTime time);

	
    @Query("SELECT r FROM Booking r " +
            "WHERE r.bookingdate = :specificDate " +
            "AND :startTime >= r.startTimeField AND :startTime <= r.endTimeField ")
    List<Booking> findByDateAndTimeRange(@Param("specificDate") Date specificDate,
                                            @Param("startTime") LocalTime startTime);

	
	
}
