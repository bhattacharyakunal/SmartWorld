package com.javasmartwork.io.healthcare.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javasmartwork.io.healthcare.model.User;
@Repository
public interface UserRepository extends MongoRepository<User, String>{
	public User findByUserName(String userName);
}
