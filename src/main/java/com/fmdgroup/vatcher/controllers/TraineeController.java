package com.fmdgroup.vatcher.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	//HOW TO
	//Add an attribute called "books" to a model object, sets its value to the result of 
	//calling the "findAll()" method on a "bookRepository" object. 
	//This retrieves all the books from a database, 
	//and makes them available to the view or template being rendered by the application.
}
