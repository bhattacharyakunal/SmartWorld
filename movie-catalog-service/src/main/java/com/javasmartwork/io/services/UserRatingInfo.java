package com.javasmartwork.io.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.javasmartwork.io.model.Rating;
import com.javasmartwork.io.model.UserRatingDetail;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingInfo {

	@Autowired RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRatingDetail getUserRating(@PathVariable("userId") String userId) {
		return restTemplate.getForObject("http://rating-data-service/ratingdata/user/"+userId, UserRatingDetail.class);
	}
	
	private UserRatingDetail getFallbackUserRating(@PathVariable("userId") String userId) {
		UserRatingDetail userRatingDetail=new UserRatingDetail();
		Rating rating=new Rating();
		rating.setMovieId("");
		rating.setRating(0);
		userRatingDetail.setUserId(userId);
		userRatingDetail.setRatingList(Arrays.asList(rating));
		return userRatingDetail;
	}
}
