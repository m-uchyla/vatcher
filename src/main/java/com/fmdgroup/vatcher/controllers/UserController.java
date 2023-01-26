package com.fmdgroup.vatcher.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;

	//private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
// /users path to the list of users
	@RequestMapping("/users")
	public String getUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "singleUser";
		// singleUser is a view, it is name of html file that makes view.
	}
//GetMapping allows user to open a website with places with input a data. 
	@RequestMapping(method = RequestMethod.GET, value = "/addUser")
	@GetMapping(value = "/addUser")
	public String goToIndex(ModelMap model) {

		return "addUser";
	}

	@PostMapping(value = "/addUser")
	public String createNewUser(ModelMap model, @ModelAttribute SingleUser user) {
		
		userRepository.save( user);
		return "redirect:/users";
		// redirect:/users -> after new user is created it redirects to path/users

	}

}
