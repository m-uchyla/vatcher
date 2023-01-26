package com.fmdgroup.vatcher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmdgroup.vatcher.repositories.SalesManagerRepository;

@Controller
public class SalesManagerController {
	private final SalesManagerRepository salesManagerRepository;

	public SalesManagerController(SalesManagerRepository salesManagerRepository) {
		super();
		this.salesManagerRepository = salesManagerRepository;
	}
	
	@RequestMapping("/salesManager")
	public String getSalesManagers(Model model) {
		model.addAttribute("salesManager", salesManagerRepository.findAll());
		return "salesManager";
	}

}
