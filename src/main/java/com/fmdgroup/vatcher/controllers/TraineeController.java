package com.fmdgroup.vatcher.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.TraineeRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;
import com.fmdgroup.vatcher.services.ITraineeService;
import com.fmdgroup.vatcher.services.TraineeService;

@Controller
public class TraineeController {

	
	@Autowired
	private ITraineeService service;
	private TraineeService traineeService;
	private final TraineeRepository traineeRepository;
	private UserRepository userRepository;

	public TraineeController(TraineeRepository traineeRepository, UserRepository userRepository) {
		super();
		this.traineeRepository = traineeRepository;
	}
	@RequestMapping(method = RequestMethod.POST, value = "/trainees")
	
	public String saveTrainee(@RequestBody Trainee trainee) {
		traineeService.addTrainee(trainee);
	        return "addUser" ;
	    
	       
	    }
	
	@RequestMapping("/trainees")
	public String getTrainees(Model model) {
		model.addAttribute("trainees", traineeRepository.findAll());
		return "trainee";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addQualification/{id}")
	
	public String updateQualification(ModelMap model ,@RequestParam String qualification, @PathVariable Long id)  {
		Set<String> traineeQualifications = service.traineeQualification();
		traineeQualifications.add(qualification);
		model.addAttribute(qualification, traineeQualifications);
		
		return "singleUser";

		

	}
	
	// this is for retrieving job offers applied by the trainee user:
	@RequestMapping("/trainees/{id}/jobOpportunities")							
	public String getJobOpportunities(@PathVariable Long id, Model model) {
	  try {
	    Set<JobOpportunity> jobOpportunities = service.getJobOpportunities(id);
	    model.addAttribute("jobOpportunities", jobOpportunities);
	    return "jobOpportunities";
	  } catch (Exception e) {
	    return "error";
	  }
	}
	

}
