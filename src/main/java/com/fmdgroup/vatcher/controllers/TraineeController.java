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

import com.fasterxml.jackson.databind.ObjectMapper;
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
	ObjectMapper objectMapper;
	@Autowired
	private ITraineeService service;
	private TraineeService traineeService;
	private final TraineeRepository traineeRepository;
	private UserRepository userRepository;

	public TraineeController(TraineeRepository traineeRepository, UserRepository userRepository) {
		super();
		this.traineeRepository = traineeRepository;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addtrainee")

	public String addTrainee(ModelMap model, @ModelAttribute Trainee trainee) throws Exception {
		service.addTrainee(trainee);
		populateModel(model);
		return "singleUser";

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deletetrainee")

	public String deleteTrainee(ModelMap model, @ModelAttribute Trainee trainee) throws Exception {
		service.deleteTrainee(trainee.getId());
		populateModel(model);
		return "singleUser";

	}
	
	
	@RequestMapping("/trainees")
	public String getTrainees(Model model) {
		model.addAttribute("trainees", traineeRepository.findAll());
		return "singleUser";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addJobPreferences/{id}")
	public String updatePreferences(ModelMap model, @RequestParam String jobPreference, @PathVariable Long id)
			throws Exception {

		service.updateJobPreferences(jobPreference, id);
		populateModel(model);
		return "singleUser";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/addQualification/{id}")

	public String updateQualification(ModelMap model, @RequestParam String qualification, @PathVariable Long id)
			throws Exception {

		service.updateQualification(qualification, id);
		populateModel(model);
		return "singleUser";

	}

	private void populateModel(ModelMap model) {
		model.addAttribute("trainees", service.findAllTrainee());
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
