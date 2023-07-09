package com.cafe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cafe.dao.BookingDao;
import com.cafe.dto.BookingDto;
import com.cafe.entity.Booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookingServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {
    @MockBean
    private BookingDao bookingDao;

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @MockBean
    private CoffeeItemService coffeeItemService;

    
    @Test
    void testBookTableForUser() {
        Booking booking = new Booking();
        booking.setAmount(10L);
        booking.setBooking_id(1L);
        booking.setBookingdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        booking.setCustid(1L);
        booking.setEndTimeField(LocalTime.MIDNIGHT);
        booking.setStartTimeField(LocalTime.MIDNIGHT);
        booking.setTable_id(1L);
        when(bookingDao.save(Mockito.<Booking>any())).thenReturn(booking);
        when(coffeeItemService.amountForUserBooking(Mockito.<Long>any())).thenReturn(10L);
        doNothing().when(coffeeItemService).deleteAllCoffeeItemsOfUser(Mockito.<Long>any());
        assertSame(booking, bookingServiceImpl.BookTableForUser(new BookingDto()));
        verify(bookingDao).save(Mockito.<Booking>any());
        verify(coffeeItemService).amountForUserBooking(Mockito.<Long>any());
        verify(coffeeItemService).deleteAllCoffeeItemsOfUser(Mockito.<Long>any());
    }

    
   

   
    @Test
    void testGetAllTheBookingOfUser() {
        ArrayList<Booking> bookingList = new ArrayList<>();
        when(bookingDao.findBycustid(Mockito.<Long>any())).thenReturn(bookingList);
        List<Booking> actualGetAllTheBookingOfUserResult = bookingServiceImpl.GetAllTheBookingOfUser(1L);
        assertSame(bookingList, actualGetAllTheBookingOfUserResult);
        assertTrue(actualGetAllTheBookingOfUserResult.isEmpty());
        verify(bookingDao).findBycustid(Mockito.<Long>any());
    }

    
    @Test
    void testGetAvailableTables() {
        when(bookingDao.findByDateAndTimeRange(Mockito.<Date>any(), Mockito.<LocalTime>any()))
                .thenReturn(new ArrayList<>());
        assertTrue(
                bookingServiceImpl
                        .GetAvailableTables(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                                LocalTime.MIDNIGHT)
                        .isEmpty());
        verify(bookingDao).findByDateAndTimeRange(Mockito.<Date>any(), Mockito.<LocalTime>any());
    }

    
    @Test
    void testGetAvailableTables2() {
        Booking booking = new Booking();
        booking.setAmount(10L);
        booking.setBooking_id(1L);
        booking.setBookingdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        booking.setCustid(1L);
        booking.setEndTimeField(LocalTime.MIDNIGHT);
        booking.setStartTimeField(LocalTime.MIDNIGHT);
        booking.setTable_id(1L);

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        when(bookingDao.findByDateAndTimeRange(Mockito.<Date>any(), Mockito.<LocalTime>any())).thenReturn(bookingList);
        Set<Long> actualGetAvailableTablesResult = bookingServiceImpl.GetAvailableTables(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), LocalTime.MIDNIGHT);
        assertEquals(1, actualGetAvailableTablesResult.size());
        assertTrue(actualGetAvailableTablesResult.contains(1L));
        verify(bookingDao).findByDateAndTimeRange(Mockito.<Date>any(), Mockito.<LocalTime>any());
    }

    
    @Test
    void testGetAvailableTables3() {
        Booking booking = new Booking();
        booking.setAmount(10L);
        booking.setBooking_id(1L);
        booking.setBookingdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        booking.setCustid(1L);
        booking.setEndTimeField(LocalTime.MIDNIGHT);
        booking.setStartTimeField(LocalTime.MIDNIGHT);
        booking.setTable_id(1L);

        Booking booking2 = new Booking();
        booking2.setAmount(1L);
        booking2.setBooking_id(2L);
        booking2.setBookingdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        booking2.setCustid(2L);
        booking2.setEndTimeField(LocalTime.MIDNIGHT);
        booking2.setStartTimeField(LocalTime.MIDNIGHT);
        booking2.setTable_id(2L);

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking2);
        bookingList.add(booking);
        when(bookingDao.findByDateAndTimeRange(Mockito.<Date>any(), Mockito.<LocalTime>any())).thenReturn(bookingList);
        Set<Long> actualGetAvailableTablesResult = bookingServiceImpl.GetAvailableTables(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), LocalTime.MIDNIGHT);
        assertEquals(2, actualGetAvailableTablesResult.size());
        assertTrue(actualGetAvailableTablesResult.contains(1L));
        assertTrue(actualGetAvailableTablesResult.contains(2L));
        verify(bookingDao).findByDateAndTimeRange(Mockito.<Date>any(), Mockito.<LocalTime>any());
    }

    
    @Test
    void testGetAllBookings() {
        ArrayList<Booking> bookingList = new ArrayList<>();
        when(bookingDao.findAll()).thenReturn(bookingList);
        List<Booking> actualAllBookings = bookingServiceImpl.getAllBookings();
        assertSame(bookingList, actualAllBookings);
        assertTrue(actualAllBookings.isEmpty());
        verify(bookingDao).findAll();
    }
}

