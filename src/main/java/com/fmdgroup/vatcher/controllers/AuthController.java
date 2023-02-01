package com.fmdgroup.vatcher.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.security.SingleUserValidator;
import com.fmdgroup.vatcher.services.RegistrationService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private final SingleUserValidator singleUserValidator;
	private final RegistrationService registrationService;
	
	@Autowired
	public AuthController(SingleUserValidator singleUserValidator, RegistrationService registrationService) {
	
		this.singleUserValidator = singleUserValidator;
		this.registrationService = registrationService;
	}

	@GetMapping("/login")
	public String loginPage() {
		return "auth/login";
	}
	
	@GetMapping("/registration")
	public String registrationPage(@ModelAttribute("singleUser") SingleUser singleUser) {
		
		return "auth/registration";
	}
	
	@PostMapping("/registration")
	public String performRegistration(@ModelAttribute("singleUser") @Valid SingleUser singleUser,BindingResult bindingResult) {
		singleUserValidator.validate(singleUser, bindingResult);
		if(bindingResult.hasErrors()) {
			return "/auth/registration";
		}
		
		registrationService.register(singleUser);
		
		return "redirect:/auth/login";
	}
	
	
}
