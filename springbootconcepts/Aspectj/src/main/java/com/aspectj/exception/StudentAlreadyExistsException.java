package com.aspectj.exception;

public class StudentAlreadyExistsException extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentAlreadyExistsException(String msg) {
		super(msg);
	}

}
