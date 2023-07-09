package com.cafe.dto;

import java.time.LocalTime;
import java.util.Date;



public class BookingDto {
	
	
	private Long Table_id;
	
	private Long custid;
	
	private Long Amount;
	
	
	
	private Date bookingdate;
	
	
	
    private LocalTime startTimeField;
	
    private LocalTime endTimeField;

	public BookingDto(Long table_id, Long custid, Long amount, Date bookingdate, LocalTime startTimeField,
			LocalTime endTimeField) {
		super();
		Table_id = table_id;
		this.custid = custid;
		Amount = amount;
		this.bookingdate = bookingdate;
		this.startTimeField = startTimeField;
		this.endTimeField = endTimeField;
	}

	public BookingDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getTable_id() {
		return Table_id;
	}

	public void setTable_id(Long table_id) {
		Table_id = table_id;
	}

	public Long getCustid() {
		return custid;
	}

	public void setCustid(Long custid) {
		this.custid = custid;
	}

	public Long getAmount() {
		return Amount;
	}

	public void setAmount(Long amount) {
		Amount = amount;
	}

	public Date getBookingdate() {
		return bookingdate;
	}

	public void setBookingdate(Date bookingdate) {
		this.bookingdate = bookingdate;
	}

	public LocalTime getStartTimeField() {
		return startTimeField;
	}

	public void setStartTimeField(LocalTime startTimeField) {
		this.startTimeField = startTimeField;
	}

	public LocalTime getEndTimeField() {
		return endTimeField;
	}

	public void setEndTimeField(LocalTime endTimeField) {
		this.endTimeField = endTimeField;
	}

    
    
    
    
	
	

}
