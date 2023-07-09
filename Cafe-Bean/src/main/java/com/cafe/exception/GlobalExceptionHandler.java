package com.cafe.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cafe.payload.ApiResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value=ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundException(ResourceNotFoundException ex)
	{
		ApiResponse response=new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			response.put(field, message);
		});
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		String response = ex.getLocalizedMessage().substring(ex.getLocalizedMessage().indexOf("Duplicate"), ex.getLocalizedMessage().indexOf("for"));
		return new ResponseEntity<String>(response, HttpStatus.IM_USED);
	}


	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		return new ResponseEntity<String>("Please change the HTTP Request Type", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
		return new ResponseEntity<String>("No data found in the database for the above entered ID", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		return new ResponseEntity<String>("Please Check the entered json data", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		return new ResponseEntity<String>("Please Check the URL, i.e. Missing Parameters", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<String> handleMissingPathVariableException(MissingPathVariableException ex) {
		return new ResponseEntity<String>("Please Check the URL, i.e. Missing Path Variables", HttpStatus.BAD_REQUEST);
	}

}
