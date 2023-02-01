package com.fmdgroup.vatcher.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fmdgroup.vatcher.model.SingleUser;
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
		
		SingleUser user1 = new SingleUser("Jan", "jan@nowak.com", "admin1","ROLE_USER");
		SingleUser user2 = new SingleUser("Ewa", "ewa@nowak.com", "admin2", "ROLE_ADMIN");
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		System.out.println("Number of users: "+userRepository.count());
	}

}
