package com.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.Service.RatingServiceImpl;
import com.rating.model.Rating;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	RatingServiceImpl ratingServiceImpl;
	
	@PostMapping
	public ResponseEntity<Rating> addRating(@RequestBody Rating rating){
		Rating addRatingResponse =  ratingServiceImpl.saveRating(rating);
		return new ResponseEntity<>(addRatingResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/getByUserId/{userId}")
	public ResponseEntity<List<Rating>> getByUserId(@PathVariable String userId){
		List<Rating> ratingResponse = ratingServiceImpl.getRatingByUserId(userId);
		return new ResponseEntity<>(ratingResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getByHotelId/{hotelId}")
	public ResponseEntity<List<Rating>> getByHotelId(@PathVariable String hotelId){
		List<Rating> ratingResponse = ratingServiceImpl.getRatingByHotelId(hotelId);
		return new ResponseEntity<>(ratingResponse, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating(){
		List<Rating> ratings = ratingServiceImpl.getRatings();
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}
}
