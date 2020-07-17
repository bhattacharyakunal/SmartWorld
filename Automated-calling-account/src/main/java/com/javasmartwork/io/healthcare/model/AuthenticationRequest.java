package com.javasmartwork.io.healthcare.model;

public class AuthenticationRequest {
	
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + userName + ", password=" + password + "]";
	}
}
