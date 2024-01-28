package com.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userservice.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserExistsException.class)
	ResponseEntity<ApiResponse> handlerUserExistsException(UserExistsException ex) {
		String message = ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).status(true).httpStatus(HttpStatus.BAD_REQUEST)
				.build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(UserNotExistException.class)
	ResponseEntity<ApiResponse> handlerUsersNotExistException(UserNotExistException ex) {
		String message = ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).status(true).httpStatus(HttpStatus.NOT_FOUND)
				.build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

}
