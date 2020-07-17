package com.javasmartwork.io.healthcare.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javasmartwork.io.healthcare.model.User;
import com.javasmartwork.io.healthcare.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired UserRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;
	
	public User isValidUser(User user) {
		return user;
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		System.out.println(user);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), passwordEncoder.encode(user.getPassword()), new ArrayList<>());
	}
}
