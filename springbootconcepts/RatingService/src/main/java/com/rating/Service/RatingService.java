package com.rating.Service;

import java.util.List;

import com.rating.model.Rating;

public interface RatingService {
	
	Rating saveRating(Rating rating);
	
	List<Rating> getRatings();
	
	List<Rating>  getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);

}
