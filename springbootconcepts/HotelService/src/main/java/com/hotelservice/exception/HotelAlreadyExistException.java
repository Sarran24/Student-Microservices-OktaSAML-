package com.hotelservice.exception;

public class HotelAlreadyExistException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HotelAlreadyExistException(String msg) {
		super(msg);
	}

}
