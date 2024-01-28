package com.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.exception.UserExistsException;
import com.userservice.exception.UserNotExistException;
import com.userservice.model.Hotel;
import com.userservice.model.Rating;
import com.userservice.model.User;
import com.userservice.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User saveUser(User user) throws UserExistsException {
		
	Optional<User> userData = userRepository.findById(user.getId());
	if(userData.isPresent()) {
		throw new UserExistsException("user already exist");
	}
	return userRepository.save(user);
	
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new UserNotExistException("No Users are present");
		}
		ResponseEntity<ArrayList<Rating>> ratingResponse = restTemplate.exchange(
		        "http://RATING-SERVICE/rating",
		        HttpMethod.GET,
		        null,
		        new ParameterizedTypeReference<ArrayList<Rating>>() {});
	    ArrayList<Rating> ratings = ratingResponse.getBody();
	    logger.info("{}", ratings);
	    for(User user : users) {
	    	ArrayList<Rating> userRating = new ArrayList<>();
	    	for(Rating rating : ratings) {
	    		if(user.getId().equals(rating.getUserId())) {
	    			userRating.add(rating);
	    		}
	    	}
	    	user.setRatings(userRating);
	    }
		
		return users;
	}

	
// this is also correct but this is not type safe	
//	@Override
//	public User getUser(String id) {
//		User user= userRepository.findById(id).orElseThrow(() -> new UserNotExistException("No user found"));
//		//fetch rating of the above user from the rating service.
//		ArrayList<Rating> ratings = restTemplate.getForObject("http://localhost:7073/rating/"+user.getId(),ArrayList.class);
//		logger.info("{}", ratings);
//		user.setRatings(ratings);
//		return user;
//	}
	
	
//this is also correct	
//	public User getUser(String id) {
//	    User user = userRepository.findById(id).orElseThrow(() -> new UserNotExistException("No user found"));
//	    // fetch rating of the above user from the rating service.
//	    ResponseEntity<ArrayList<Rating>> ratingResponse = restTemplate.exchange(
//	        "http://localhost:7073/rating/getByUserId/" + user.getId(),
//	        HttpMethod.GET,
//	        null,
//	        new ParameterizedTypeReference<ArrayList<Rating>>() {});
//	    ArrayList<Rating> ratings = ratingResponse.getBody();
//	    logger.info("{}", ratings);
//	    user.setRatings(ratings);
//	    return user;
//	}
	
	//this will give the hotel details in rating
	public User getUser(String id) {
	    User user = userRepository.findById(id).orElseThrow(() -> new UserNotExistException("No user found"));
	    // fetch rating of the above user from the rating service.
	    ResponseEntity<ArrayList<Rating>> ratingResponse = restTemplate.exchange(
	        "http://RATING-SERVICE/rating/getByUserId/" + user.getId(),
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<ArrayList<Rating>>() {});
	    ArrayList<Rating> ratings = ratingResponse.getBody();
	    logger.info("{}", ratings);
	    for(Rating rating : ratings) {
	    	  ResponseEntity<Hotel> HotelResponse = restTemplate.exchange(
	  		        "http://HOTEL-SERVICE/hotel/"+rating.getHotelId(),
	  		        HttpMethod.GET,
	  		        null,
	  		        new ParameterizedTypeReference<Hotel>() {});
	  	    Hotel hotel = HotelResponse.getBody();
	  	    logger.info("{}", hotel);
	  	    rating.setHotel(hotel);
	    }
	  

//	    for(Rating rating : ratings) {
//	    	ArrayList<Hotel> hotelsDetails = new ArrayList<>();
//	    	for(Hotel hotel : hotels) {
//	    		if(rating.getHotelId().equals(hotel.getId())) {
//	    			hotelsDetails.add(hotel);
//	    		}
//	    	}
//	    	rating.setHotel(hotels);
//	    }
	    user.setRatings(ratings);
	    return user;
	}

	@Override
	public void deleteUser(String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotExistException("No user found"));
		userRepository.deleteById(user.getId());
	}

}
