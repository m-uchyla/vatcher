package com.fmdgroup.vatcher.services;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.Trainee;

public interface ITraineeService {
	void updateJobPreferences(String qualification ,Long id) throws Exception;
	void updateQualification(String qualification ,Long id) throws Exception;
	Trainee findTraineeById(Long id) throws Exception;
	void deleteTrainee(Long id) throws Exception;
	String addTrainee(Trainee trainee) throws Exception;
	List<Trainee> findAllTrainee();
  	Set<JobOpportunity> getJobOpportunities(Long traineeId) throws Exception;	// this is for retrieving job offers applied by the trainee user:
	Set<JobOpportunity> getNotAppliedJobOpportunities(Trainee trainee);			//job offers NOT applied for by trainees
	//Set<String> traineeQualification(Long id);
	Trainee findTraineeByUsername(String username) throws Exception;
	void addJobOpportunity(Long traineeId, Long jobId) throws Exception;	
}
