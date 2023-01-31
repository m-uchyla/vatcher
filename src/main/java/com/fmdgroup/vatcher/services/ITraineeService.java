package com.fmdgroup.vatcher.services;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import com.fmdgroup.vatcher.model.Trainee;

public interface ITraineeService {
	Set<String> traineeQualification();
	void updateQualification(String qualification ,Long id) throws Exception;
	Trainee findTraineeById(Long id) throws Exception;
	String addTrainee(Trainee trainee) throws Exception;
	List<Trainee> findAllTrainee();
}
