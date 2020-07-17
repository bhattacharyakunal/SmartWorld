package com.javasmartwork.io.healthcare.model;

public class AuthenticationResponse {
	private String jwtToken;
	
	public AuthenticationResponse() {
	}
	
	public AuthenticationResponse(String jwt) {
		this.jwtToken = jwt;
	}

	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
