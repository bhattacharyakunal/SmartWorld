package com.javasmartwork.io.healthcare.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasmartwork.io.healthcare.model.AuthenticationRequest;
import com.javasmartwork.io.healthcare.model.AuthenticationResponse;
import com.javasmartwork.io.healthcare.model.User;
import com.javasmartwork.io.healthcare.service.UserService;
import com.javasmartwork.io.healthcare.util.JwtUtil;

@RestController
@RequestMapping("/userResource")
@CrossOrigin("*")
public class UserResource {
	
	@Autowired UserService userService;
	@Autowired AuthenticationManager authManager;
	@Autowired JwtUtil jwtUtil;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user){
		user = userService.addUser(user);
		if(null != user) {
			return ResponseEntity.accepted().body(userService.addUser(user));
		}else {
			return ResponseEntity.unprocessableEntity().body(null);
		}
	}
	
	@PostMapping("/validateUser")
	public ResponseEntity<?> isValidUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
		authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
				);
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new Exception("Bad Credentials", ex);
		}
		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());
		String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<?> getValidUser(){
		User validUser = new User();
		validUser.setUserName("kunal");
		validUser.setPassword("password");
			return ResponseEntity.ok(validUser);
	}
}
