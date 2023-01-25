package com.fmdgroup.vatcher.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fmdgroup.vatcher.repositories.TraineeRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;

@Controller
public class TraineeController {
	
	private final TraineeRepository traineeRepository;
	private final UserRepository userRepository;

	public TraineeController(TraineeRepository traineeRepository, UserRepository userRepository) {
		super();
		this.traineeRepository = traineeRepository;
		this.userRepository = userRepository;
	}
	
	@RequestMapping("/users")
	public String getUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "index";
	}
	
	
	@RequestMapping("/trainees")
	public String getTrainees(Model model) {
		model.addAttribute("trainees", traineeRepository.findAll());
		return "trainees/list";
	}
	

}
