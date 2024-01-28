package com.hotelservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelservice.exception.HotelAlreadyExistException;
import com.hotelservice.exception.HotelNotFoundException;
import com.hotelservice.model.Hotel;
import com.hotelservice.repo.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel saveHotel(Hotel hotel) {
		Optional<Hotel> existHotel = hotelRepository.findById(hotel.getId());
		if (existHotel.isPresent()) {
			throw new HotelAlreadyExistException("The hotel is already exist with same id...");
		}
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotelById(String id) {
		return hotelRepository.findById(id)
				.orElseThrow(() -> new HotelNotFoundException("there is no hotel with this id"));
	}

	@Override
	public List<Hotel> getHotels() {
		List<Hotel> hotels = hotelRepository.findAll();
		if (hotels.isEmpty()) {
			throw new HotelNotFoundException("there is no hotels");
		}
		return hotels;
	}

	@Override
	public void deleteHotel(String id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if (!hotel.isPresent()) {
			throw new HotelNotFoundException(id);
		} else {
			hotelRepository.deleteById(id);
		}

	}

}
