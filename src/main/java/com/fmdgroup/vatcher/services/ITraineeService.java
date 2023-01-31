package com.fmdgroup.vatcher.services;

import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.Trainee;

public interface ITraineeService {
	Set<String> traineeQualification();
	void updateQualification(String qualification ,Long id) throws Exception;
	Trainee findTraineeById(Long id) throws Exception;
	String addTrainee(Trainee trainee);
  	Set<JobOpportunity> getJobOpportunities(Long traineeId) throws Exception;	// this is for retrieving job offers applied by the trainee user:

}
