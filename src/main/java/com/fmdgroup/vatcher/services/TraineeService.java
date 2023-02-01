package com.fmdgroup.vatcher.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;
import com.fmdgroup.vatcher.repositories.TraineeRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;
@Service
public class TraineeService implements ITraineeService {

private TraineeRepository traineeRepo;

private JobOpportunityService jobOpportunityService;

public TraineeService(JobOpportunityService jobOpportunityService) {
	super();
	this.jobOpportunityService = jobOpportunityService;
	
}

	@Override
	public Set<String> traineeQualification() {
	
		return null;
	}
	
	// this is for retrieving job offers applied by the trainee user:
	@Override				
	public Set<JobOpportunity> getJobOpportunities(Long traineeId) throws Exception {
	  Trainee trainee = findTraineeById(traineeId);
	  return trainee.getJobOpportunities();
	}
	
	//this is for retrieving job offers the trainee did NOT apply for:
	  @Override
	    public Set<JobOpportunity> getNotAppliedJobOpportunities(Trainee trainee) {
		  Set<JobOpportunity> notApplied = new HashSet();
	        Set<String> appliedJobTitles = trainee.getAppliedJobTitles();
	       
	        List<JobOpportunity> allJobOpportunities;
			try {
				allJobOpportunities = jobOpportunityService.getAllJobOpportunities();
	        if (allJobOpportunities == null || allJobOpportunities.isEmpty()) {
	            throw new IllegalArgumentException("No job opportunities found.");
	        }
	        return allJobOpportunities.stream().filter(jobOpportunity -> !appliedJobTitles.contains(jobOpportunity.getJobTitle()))
	            .collect(Collectors.toSet());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return notApplied;
				
			}
	    }
	  

	@Override
	public Trainee findTraineeById(Long id) throws Exception {
		Optional<Trainee> optTrainee = traineeRepo.findById(id);
		return optTrainee.orElseThrow(()-> new Exception());
	}

	@Override
	public void updateQualification(String qualification, Long id) throws Exception {
		Trainee trainee = findTraineeById(id);
		Set<String> traineeQualification = trainee.getQualifications();
		traineeQualification.add(qualification);
		traineeRepo.save(trainee);
		
	}


	


	@Override
	public String addTrainee(Trainee trainee) {
		traineeRepo.save(trainee);
		return trainee.getUser().getName();
	}

	
	
	
}
