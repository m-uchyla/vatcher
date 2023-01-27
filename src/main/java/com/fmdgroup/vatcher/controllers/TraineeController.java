package com.fmdgroup.vatcher.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.TraineeRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;

@Controller
public class TraineeController {

	private final TraineeRepository traineeRepository;

	public TraineeController(TraineeRepository traineeRepository, UserRepository userRepository) {
		super();
		this.traineeRepository = traineeRepository;
	}

	@RequestMapping("/trainees")
	public String getTrainees(Model model) {
		model.addAttribute("trainees", traineeRepository.findAll());
		return "trainee";
	}

	@RequestMapping("/addQualification")
	public Set<String> addQualification(Long id, String qualification) throws Exception {

		Optional<Trainee> trainee = traineeRepository.findById(id);
		if (trainee.isPresent()) {
			Set<String> traineeQualifications = trainee.get().getQualifications();
			traineeQualifications.add(qualification);
			traineeRepository.save(trainee.get());

			return trainee.get().getJobsPreferences();
		} else {
			return trainee.get().getJobsPreferences();
		}

	}

}
