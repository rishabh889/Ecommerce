package com.sarvika.authenticationservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sarvika.authenticationservice.entity.User;

@Component
public class DatabaseInitializer implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		User admin= new User();
		admin.setUsername("token");
		admin.setUsername("token123");
		userRepository.save(admin);
	}

}
