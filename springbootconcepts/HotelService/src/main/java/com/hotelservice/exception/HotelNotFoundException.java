package com.hotelservice.exception;

public class HotelNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HotelNotFoundException(String msg) {
		super(msg);
	}

}
