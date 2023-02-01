package com.fmdgroup.vatcher.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.TraineeRepository;
@Service
public class TraineeService implements ITraineeService {

private TraineeRepository traineeRepo;

	
	
	// this is for retrieving job offers applied by the trainee user:
	@Override				
	public Set<JobOpportunity> getJobOpportunities(Long traineeId) throws Exception {
	  Trainee trainee = findTraineeById(traineeId);
	  return trainee.getJobOpportunities();
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

	


	
	
}
