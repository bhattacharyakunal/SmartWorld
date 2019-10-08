package com.javasmartwork.io.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javasmartwork.io.model.Movie;
import com.javasmartwork.io.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfo {
	@Autowired private RestTemplate restTemplate;
	@HystrixCommand(fallbackMethod = "getFallbackMovieDetail")
	public Movie getMovieDetail(Rating rating) {
		return restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
	}
	
	private Movie getFallbackMovieDetail(Rating rating) {
		return new Movie("", "No Movie Found");
	}
}
