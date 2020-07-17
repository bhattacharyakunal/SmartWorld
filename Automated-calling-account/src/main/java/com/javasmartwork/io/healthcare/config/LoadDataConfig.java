package com.javasmartwork.io.healthcare.config;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.javasmartwork.io.healthcare.model.User;
import com.javasmartwork.io.healthcare.repository.UserRepository;
@Component
public class LoadDataConfig implements CommandLineRunner {

	@Autowired private UserRepository userRepository;
	
	@Value("${autoCallerDefaultUser.username}")
	private String defaultUserName;
	@Value("${autoCallerDefaultUser.passkey}")
	private String defaultPasskey;
	@Value("${isDefaultUserCreationEnable}")
	private String isDefaultUserCreationEnable;
	
	
	@Override
	public void run(String... args) throws Exception {
		if(isDefaultUserCreationEnable.equalsIgnoreCase("Y")) {
			CompletableFuture.runAsync(()->{
				User user = userRepository.findByUserName(defaultUserName);
				if(null == user) {
					user = new User();
					user.setUserName(defaultUserName);
					user.setPassword(defaultPasskey);
					user.setRoles(new ArrayList<>());
					userRepository.save(user);
					System.out.println("Data Saved");
				}
			});
		}
	}

}
