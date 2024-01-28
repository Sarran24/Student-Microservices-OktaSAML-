package com.aspectj.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value= NoSuchStudentExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleException(NoSuchStudentExistsException e) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage());
	}

}
