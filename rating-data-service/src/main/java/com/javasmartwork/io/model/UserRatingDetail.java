package com.javasmartwork.io.model;

import java.util.List;

public class UserRatingDetail {
	private String userId;
	private List<Rating> ratingList;
	public UserRatingDetail() {
		super();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Rating> getRatingList() {
		return ratingList;
	}
	public void setRatingList(List<Rating> ratingList) {
		this.ratingList = ratingList;
	}
}
