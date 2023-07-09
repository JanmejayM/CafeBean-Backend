package com.cafe.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	private String message;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
