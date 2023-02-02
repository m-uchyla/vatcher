package com.fmdgroup.vatcher.bootstrap;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;

@Component
public class BootsStrapData implements CommandLineRunner {
	
	private final UserRepository userRepository;
	private final NotificationsRepository notificationsRepository;

	public BootsStrapData(UserRepository userRepository, NotificationsRepository notificationsRepository) {
		super();
		this.userRepository = userRepository;
		this.notificationsRepository = notificationsRepository;
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Started in Bootstrap");
		
		SingleUser user1 = new SingleUser("Jan", "jan@nowak.com", "admin1","ROLE_USER");
		SingleUser user2 = new SingleUser("Ewa", "ewa@nowak.com", "admin2", "ROLE_ADMIN");
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		System.out.println("Number of users: "+userRepository.count());
		
	Notifications not1 = new Notifications("You have a notification.", user1, new Date() , false);

	notificationsRepository.save(not1);
	
	}
}
