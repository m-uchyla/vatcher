package com.fmdgroup.vatcher.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;

@Component
public class BootsStrapData implements CommandLineRunner {
	
	private final UserRepository userRepository;
	private final JobOpportunityRepository jobOpportunityRepository; 

	public BootsStrapData(UserRepository userRepository, JobOpportunityRepository jobOpportunityRepository) {
		super();
		this.userRepository = userRepository;
		this.jobOpportunityRepository = jobOpportunityRepository;
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
	
	public void runJobOpportunities(String... args) throws Exception {
	 
	 JobOpportunity jobOpportunity1 = new JobOpportunity("Software Enginner", "Shell", "Warsaw", "Office");
	 JobOpportunity jobOpportunity2 = new JobOpportunity("Data Enginner", "HSBC", "Wroclaw", "Remote");
	 JobOpportunity jobOpportunity3 = new JobOpportunity("Software Developer", "Google", "New York", "Office");
	 JobOpportunity jobOpportunity4 = new JobOpportunity("Software tester", "Amazon", "Warsaw", "Remote");
	 JobOpportunity jobOpportunity5 = new JobOpportunity("Software Developer", "Facebook", "Frankfurt", "Office");
	 
	 jobOpportunityRepository.save(jobOpportunity1);
	 jobOpportunityRepository.save(jobOpportunity2);
	 jobOpportunityRepository.save(jobOpportunity3);
	 jobOpportunityRepository.save(jobOpportunity4);
	 jobOpportunityRepository.save(jobOpportunity5);
		

}

}
