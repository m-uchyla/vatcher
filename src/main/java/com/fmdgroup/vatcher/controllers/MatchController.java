package com.fmdgroup.vatcher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmdgroup.vatcher.repositories.MatchRepository;

@Controller
public class MatchController {
	private final MatchRepository matchRepository;

	public MatchController(MatchRepository matchRepository) {
		super();
		this.matchRepository = matchRepository;
	}
	
	@RequestMapping("/match")
	public String getUsers(Model model) {
		model.addAttribute("match", matchRepository.findAll());
		return "match";
	}
}
