package com.hotelservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelservice.model.Hotel;
import com.hotelservice.service.HotelServiceImpl;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	
	@PostMapping
	public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
		Hotel hotelResponse = 	hotelServiceImpl.saveHotel(hotel);
		return new ResponseEntity<>(hotelResponse,HttpStatus.OK) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String id){
		Hotel hotelResponse = hotelServiceImpl.getHotelById(id);
		return new ResponseEntity<>(hotelResponse, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel(){
		List<Hotel> hotels = hotelServiceImpl.getHotels();
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteHotelById(@PathVariable String id){
		hotelServiceImpl.deleteHotel(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
