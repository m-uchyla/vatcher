package com.fmdgroup.vatcher.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fmdgroup.vatcher.repositories.TraineeRepository;

@Controller
public class TraineeController {
	
	private final TraineeRepository traineeRepository;

	public TraineeController(TraineeRepository traineeRepository) {
		super();
		this.traineeRepository = traineeRepository;
	}
	
	
	@RequestMapping("/trainees")
	public String getTrainees(Model model) {
		model.addAttribute("trainees", traineeRepository.findAll());
		return "trainees/list";
	}
	

}
