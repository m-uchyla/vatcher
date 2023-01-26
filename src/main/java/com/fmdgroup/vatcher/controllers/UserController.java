package com.fmdgroup.vatcher.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	
	@RequestMapping(method=RequestMethod.GET, value="/addUser")
	@GetMapping(value="/addUser")
	public String goToIndex(ModelMap model) {
		
		return "addUser";
	}



}
