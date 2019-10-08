package com.javasmartwork.io.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasmartwork.io.model.CatalogItem;
import com.javasmartwork.io.model.Movie;
import com.javasmartwork.io.model.UserRatingDetail;
import com.javasmartwork.io.services.MovieInfo;
import com.javasmartwork.io.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired private UserRatingInfo userRatingInfo;
	@Autowired private MovieInfo movieInfo;
	
	@GetMapping("/{userId}")
//	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		UserRatingDetail userRatingDetail=userRatingInfo.getUserRating(userId);
		return userRatingDetail.getRatingList().stream().map(rating->{
			Movie movie=movieInfo.getMovieDetail(rating);
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		}).collect(Collectors.toList());
	}
	
//	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId){
//		return Arrays.asList(new CatalogItem("No Movie", "", 0));
//	}
}
