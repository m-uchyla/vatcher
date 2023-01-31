package com.fmdgroup.vatcher.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;


import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.TraineeRepository;

@Service
public class TraineeService implements ITraineeService {

	private TraineeRepository traineeRepo;

	@Override
	public Set<String> traineeQualification() {

		return null;
	}

	@Override
	public Trainee findTraineeById(Long id) throws Exception {
		Optional<Trainee> optTrainee = traineeRepo.findById(id);
		return optTrainee.orElseThrow(() -> new Exception());
	}

	@Override
	public void updateQualification(String qualification, Long id) throws Exception {
		Trainee trainee = findTraineeById(id);
		Set<String> traineeQualification = trainee.getQualifications();
		traineeQualification.add(qualification);
		traineeRepo.save(trainee);

	}

	@Override
	public String addTrainee(Trainee trainee) throws Exception {
		traineeRepo.save(trainee);
		return trainee.getUser().getName();
	}
	public List<Trainee> findAllTrainee() {
		return traineeRepo.findAll();
	}
}
