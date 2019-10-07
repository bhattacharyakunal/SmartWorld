package com.javasmartwork.io.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasmartwork.io.model.Rating;
import com.javasmartwork.io.model.UserRatingDetail;

@RestController
@RequestMapping("/ratingdata")
public class RatingDataResource {
	
	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	@GetMapping("/user/{userId}")
	public UserRatingDetail getUserRatingDetails(@PathVariable("userId") String userId) {
		List<Rating> ratingDetails = new ArrayList<>();
		ratingDetails.add(new Rating("1", 5));
		ratingDetails.add(new Rating("2", 4));
		ratingDetails.add(new Rating("3", 3));
		UserRatingDetail userRatingDetail=new UserRatingDetail();
		userRatingDetail.setUserId(userId);
		userRatingDetail.setRatingList(ratingDetails);
		return userRatingDetail;
	}
	
}
