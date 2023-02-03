package com.fmdgroup.vatcher.bootstrap;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.Match;
import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;
import com.fmdgroup.vatcher.repositories.TraineeRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;

@Component
public class BootsStrapData implements CommandLineRunner {
	
	private final UserRepository userRepository;
	private final NotificationsRepository notificationsRepository;
	private final JobOpportunityRepository jobOpportunityRepository;
	private final TraineeRepository traineeRepository;

	public BootsStrapData(UserRepository userRepository, NotificationsRepository notificationsRepository, JobOpportunityRepository jobOpportunitesRepository, TraineeRepository traineeRepository) {
		super();
		this.userRepository = userRepository;
		this.notificationsRepository = notificationsRepository;
		this.jobOpportunityRepository = jobOpportunitesRepository;
		this.traineeRepository = traineeRepository;
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Started in Bootstrap");
		
		SingleUser user1 = new SingleUser("Jan", "jan@nowak.com", "admin1","ROLE_USER");
		SingleUser user2 = new SingleUser("Ewa", "ewa@nowak.com", "admin2", "ROLE_ADMIN");
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		Set<String> testQualifications = new HashSet<String>();
		testQualifications.add("Welder");
		testQualifications.add("Soldier");
		testQualifications.add("Cook");
		testQualifications.add("Java Developer");
		testQualifications.add("Nanny");
		
		Set<String> testJobPref = new HashSet<String>();
		testJobPref.add("Soldier");
		testJobPref.add("Welder");
		testJobPref.add("Java Developer");
				
		Trainee trainee = new Trainee(user2);
		trainee.setQualifications(testQualifications);
		trainee.setJobsPreferences(testJobPref);
		traineeRepository.save(trainee);
			
		System.out.println("Number of users: "+userRepository.count());
		
//		Notifications not1 = new Notifications("You have a notification.", user1, new Date() , false);
//		notificationsRepository.save(not1);
		
		Set<String> skillSet = new HashSet<>();
		skillSet.add("Java");
		skillSet.add("SQL");
		skillSet.add("Python");
		skillSet.add("English");
		
		 JobOpportunity jobOpportunity1 = new JobOpportunity(
				 "Software Enginner", 
				 "Shell", 
				 "Warsaw", 
				 "1 year",
				 "Simple description",
				 skillSet);
		 
		 JobOpportunity jobOpportunity2 = new JobOpportunity(
				 "Data Enginner", 
				 "HSBC", 
				 "Wroclaw", 
				 "1 year",
				 "Simple description1",
				 skillSet);
		 
		 JobOpportunity jobOpportunity3 = new JobOpportunity(
				 "Software Developer", 
				 "Google", 
				 "Frankfurt", 
				 "2 year",
				 "Simple description2",
				 skillSet);
		 
		 JobOpportunity jobOpportunity4 = new JobOpportunity(
				 "Software Tester", 
				 "HSBC", 
				 "Warsaw", 
				 "2 year",
				 "Simple description3",
				 skillSet);
		 
		 JobOpportunity jobOpportunity5 = new JobOpportunity(
				 "Software Tester", 
				 "Shell", 
				 "New York", 
				 "3 year",
				 "Simple description4",
				 skillSet);
	 
	 
	 //JobOpportunity jobOpportunity2 = new JobOpportunity("Data Enginner", "HSBC", "Wroclaw", "Remote");
	 //JobOpportunity jobOpportunity3 = new JobOpportunity("Software Developer", "Google", "New York", "Office");
	// JobOpportunity jobOpportunity4 = new JobOpportunity("Software tester", "Amazon", "Warsaw", "Remote");
	 //JobOpportunity jobOpportunity5 = new JobOpportunity("Software Developer", "Facebook", "Frankfurt", "Office");
	 
	 jobOpportunityRepository.save(jobOpportunity1);
	 jobOpportunityRepository.save(jobOpportunity2);
	 jobOpportunityRepository.save(jobOpportunity3);
	 jobOpportunityRepository.save(jobOpportunity4);
	 jobOpportunityRepository.save(jobOpportunity5);
	

	 
	}
	
	
}
