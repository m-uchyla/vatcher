package com.fmdgroup.vatcher.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;
import com.fmdgroup.vatcher.services.JobOpportunityService;

@Controller
public class JobOpportunityController {
	private final JobOpportunityRepository jobOpportunityRepository; //create an instance of JobOpportunityRepository 
	private final JobOpportunityService	service;																//which is used to access the data stored in the 
																	//job opportunity repository

	public JobOpportunityController(JobOpportunityRepository jobOpportunityRepository, JobOpportunityService service) { //
		super();
		this.jobOpportunityRepository = jobOpportunityRepository;
		this.service = service;
	}
	
	@RequestMapping("/jobOpportunity")	//pobieranie //method is responsible for handling requests 
										//made to the /jobOpportunity endpoint
	//It takes a Model object as an argument, which is used to add attributes 
	//to the model that can be used to render the view
	//The method retrieves all job opportunities from the repository using the findAll() method 
	//and adds them to the model under the "jobOpportunity" attribute. The method returns a string 
	//"jobOpportunity" which is the name of the view.
	public String getJobOpportunities(Model model) {
		model.addAttribute("jobOpportunity", jobOpportunityRepository.findAll());
		return "addUser";
	}
	
	// only the active jobopportunites:

	@RequestMapping("/activeJobOpportunities")							//to handle HTTP POST requests
		public String getActiveJobOpportunities(Model model) {
		model.addAttribute("jobOpportunity", jobOpportunityRepository.findByActiveTrue());
		return "addUser"; 

	}
	
	//adding jobopportunities for admin:
////	@PreAuthorize("hasRole('Admin')")		// so olny admin can do it (needs to be added to pom file)
	
	@PostMapping("/addJobOpportunity")
	public String addJobOpportunity(@ModelAttribute JobOpportunity jobOpportunity) {		
			jobOpportunityRepository.save(jobOpportunity);
			return "redirect:/jobOpportunity";
	}
	
	//modelattribute used to bind the data from an HTML form to a Java object (the jobopportunity object)
	//allows the data from the form to be automatically populated into a JobOpportunity object and passed to the 
	//controller method as an argument
	//The @ModelAttribute is used to associate the 
	//object with the model so that the data from the form can be used in the controller method.

	@RequestMapping("/jobOpportunity/{id}")
	public String findJobOpportunityByID(@PathVariable("id") Long ID, Model model) {
		try {
			model.addAttribute("jobOpportunity", service.findJobOpportunityByID(ID));
		} catch(Exception e) {
			model.addAttribute("jobOpportunity", null);
		}
		return "addUser";
		
	}
	
	
	@GetMapping("/jobOpportunity/{salesManagerId}")
	  public List<JobOpportunity> getJobOpportunitiesBySalesManager(@PathVariable Long salesManagerId) {
	    return JobOpportunityService.findBySalesManager(salesManagerId);
	  }
	
}

