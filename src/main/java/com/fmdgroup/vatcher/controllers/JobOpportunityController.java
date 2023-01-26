package com.fmdgroup.vatcher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;

@Controller
public class JobOpportunityController {
	private final JobOpportunityRepository jobOpportunityRepository;

	public JobOpportunityController(JobOpportunityRepository jobOpportunityRepository) {
		super();
		this.jobOpportunityRepository = jobOpportunityRepository;
	}
	
	@RequestMapping("/jobOpportunity")
	public String getUsers(Model model) {
		model.addAttribute("jobOpportunity", jobOpportunityRepository.findAll());
		return "jobOpportunity";
	}
}

