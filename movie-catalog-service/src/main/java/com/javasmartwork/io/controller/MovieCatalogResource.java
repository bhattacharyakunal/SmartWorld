package com.javasmartwork.io.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javasmartwork.io.model.CatalogItem;
import com.javasmartwork.io.model.Movie;
import com.javasmartwork.io.model.UserRatingDetail;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired private RestTemplate restTemplate;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		UserRatingDetail userRatingDetail=restTemplate.getForObject("http://rating-data-service/ratingdata/user/userId", UserRatingDetail.class);
		return userRatingDetail.getRatingList().stream().map(rating->{
			Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		}).collect(Collectors.toList());
	}
}
