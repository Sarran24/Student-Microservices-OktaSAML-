package com.hotelservice.service;

import java.util.List;

import com.hotelservice.model.Hotel;

public interface HotelService {

	Hotel saveHotel(Hotel hotel);

	Hotel getHotelById(String id);

	List<Hotel> getHotels();

	void deleteHotel(String id);

}
