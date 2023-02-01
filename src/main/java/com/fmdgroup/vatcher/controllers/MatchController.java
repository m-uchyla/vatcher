package com.fmdgroup.vatcher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmdgroup.vatcher.model.Match;
import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;
import com.fmdgroup.vatcher.repositories.MatchRepository;
import com.fmdgroup.vatcher.repositories.TraineeRepository;
import com.fmdgroup.vatcher.services.IMatchService;

@Controller
public class MatchController {
	@Autowired
	private IMatchService service;
	private MatchRepository matchRepository;
	private TraineeRepository traineeRepository;
	private JobOpportunityRepository jobOppRepository;
	


	
	@PostMapping("/creatematch")
	public String createNewMatch(ModelMap model, @ModelAttribute Match match ) throws Exception{
		service.createNewMatch(match);
		populateModel(model);
		return "match";
		
	}
	@PostMapping("/deletematch")
	public String deleteMatch(ModelMap model, @PathVariable Long id) throws Exception {
		service.deleteMatch(id);
		populateModel(model);
		return "/match";
		
	}
	
	@RequestMapping("/match")
	public String getMatch(Model model) {
		model.addAttribute("match", matchRepository.findAll());
		return "match";
	}
	
	private void populateModel(ModelMap model) {
		model.addAttribute("matches", service.findAllMatches());
	}
}
