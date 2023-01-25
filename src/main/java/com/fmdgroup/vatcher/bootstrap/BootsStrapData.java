package com.fmdgroup.vatcher.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fmdgroup.vatcher.model.User;
import com.fmdgroup.vatcher.repositories.UserRepository;

@Component
public class BootsStrapData implements CommandLineRunner {
	
	private final UserRepository userRepository;
	

	public BootsStrapData(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Started in Bootstrap");
		
		User user1 = new User("Jan", "jan@nowak.com", "admin1");
		User user2 = new User("Ewa", "ewa@nowak.com", "admin2");
		User user3 = new User("Tomek", "tomek@nowak.com", "admin3");
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		System.out.println("Number of users: "+userRepository.count());
	}

}
