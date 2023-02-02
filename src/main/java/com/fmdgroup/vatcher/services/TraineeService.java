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

//
//	@Override
//	public Trainee findTraineeByUsername(String username) throws Exception {
//    Optional<Trainee> optionalTrainee = Optional.ofNullable(traineeRepo.findByUsername(username));
//    return optionalTrainee.orElse(null);
//}
		
//	implementation of the method findTraineeByUsername. takes a username
//	as input and returns a Trainee object. The method first fetches the Trainee object from 
//	the traineeRepo by calling the method findByUsername with the input username. This method 
//	returns an Optional<Trainee> object, which may contain a value of type Trainee or may be empty.
//
//	The method orElse(null) is called on the Optional<Trainee> object. It returns the value 
//	contained in the Optional if it is present, otherwise returns the specified default value
//	(in this case, null). So, if the Optional contains a Trainee object, it is returned, otherwise,
//	null is returned.
	
	
	
//	@Override
//	public Set<String> traineeQualification(Long id) {
//			
//	}
	
	//adds a specific job offer (as an object) to the trainee's offer list
	@Override
	public void addJobOpportunity(Long traineeId, Long jobId) throws Exception {
		Trainee trainee = findTraineeById(traineeId);									//retrieve a trainee objcet with the findTraineeById method and store the result in the variable 'trainee'
	    JobOpportunity job = jobOpportunityService.findJobOpportunityByID(jobId);		//retrieve jobopp object by calling the method findjobopportunitybyid from a jobopportunityservice objcet with the input jobId and store the result in the variable 'job'
	    Set<JobOpportunity> jobOpportunities = trainee.getJobOpportunities();			//get set of jobopp assosiated with the trainee object calling the method getjobopportunities and store it in the variable jobopportunities
	    jobOpportunities.add(job);														//add job objcect to the jobopportunities set
	    traineeRepo.save(trainee);														//save the updated trainee object by method save on a traineerepo object and passing in the trainee object as an argument
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
	        Set<JobOpportunity> appliedJobTitles = trainee.getJobOpportunities();
	       
	        List<JobOpportunity> allJobOpportunities;
			try { 
				allJobOpportunities = jobOpportunityService.getAllJobOpportunities();
	        if (allJobOpportunities == null || allJobOpportunities.isEmpty()) {
	            throw new IllegalArgumentException("No job opportunities found.");
	        }
	        return allJobOpportunities.stream().filter(jobOpportunity -> !appliedJobTitles.contains(jobOpportunity))
	            .collect(Collectors.toSet());
			} catch (Exception e) {
				e.printStackTrace();
				return notApplied;
				
			}
	    }
	  


	@Override
	public Trainee findTraineeById(Long id) throws Exception {
		Optional<Trainee> trainee = traineeRepo.findById(id);
		return trainee.orElseThrow(()-> new Exception());
	}
	@Override
	public void updateJobPreferences(String preference, Long id) throws Exception {
		Trainee trainee = traineeRepo.getById(id);
		Set<String> traineeJobPreferences = trainee.getJobsPreferences();
		traineeJobPreferences.add(preference);
		traineeRepo.save(trainee);
	}
	
	@Override
	public void updateQualification(String qualification, Long id) throws Exception {
		Trainee trainee = traineeRepo.getById(id);
		Set<String> traineeQualification = trainee.getQualifications();
		traineeQualification.add(qualification);
		traineeRepo.save(null);

	}

	@Override
	public String addTrainee(Trainee trainee) throws Exception {
		traineeRepo.save(trainee);
		return trainee.getUser().getName();
	}
	public List<Trainee> findAllTrainee() {
		return traineeRepo.findAll();
	}
	@Override
	public void deleteTrainee(Long id) throws Exception {
		traineeRepo.delete(findTraineeById(id));
		
	}


	public Trainee getCurrentTrainee() {
		return null;
	}

	@Override
	public Trainee findTraineeByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	



	
	
}
