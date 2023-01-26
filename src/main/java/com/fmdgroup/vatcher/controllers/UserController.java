package com.fmdgroup.vatcher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fmdgroup.vatcher.repositories.UserRepository;

@Controller
public class UserController {
	private final UserRepository userRepository;


	public UserController(UserRepository userRepository) {
	super();
	this.userRepository = userRepository;
}
	
	
	@RequestMapping("/users")
	public String getUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "singleUser";
	}



}
