package com.hotelservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotelservice.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(HotelAlreadyExistException.class)
//	ResponseEntity<ApiResponse> handlerHotelAlreadyExistException(HotelAlreadyExistException ex) {
//		String message = ex.getMessage();
//		ApiResponse response = ApiResponse.builder().message(message).status(true).httpStatus(HttpStatus.BAD_REQUEST)
//				.build();
//		return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
//	}
	
//	@ExceptionHandler(HotelNotFoundException.class)
//	ResponseEntity<ApiResponse> handlerHotelNotFoundException(HotelNotFoundException ex) {
//		String message = ex.getMessage();
//		ApiResponse response = ApiResponse.builder().message(message).status(true).httpStatus(HttpStatus.NOT_FOUND)
//				.build();
//		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<Map<String, Object>> notFoundHandler(HotelNotFoundException ex){
		Map<String, Object> map = new HashMap<>();
		map.put("message", ex.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HotelAlreadyExistException.class)
	public ResponseEntity<Map<String, Object>> notFoundHandler(HotelAlreadyExistException ex){
		Map<String, Object> map = new HashMap<>();
		map.put("message", ex.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

}
