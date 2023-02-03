package com.fmdgroup.vatcher.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fmdgroup.vatcher.model.SalesManager;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.SalesManagerRepository;
import com.fmdgroup.vatcher.repositories.TraineeRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;
import com.fmdgroup.vatcher.security.SingleUserDetails;
import com.fmdgroup.vatcher.services.RegistrationService;
import com.fmdgroup.vatcher.services.SingleUserDetailsService;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	RegistrationService regService;
	@Autowired
	SingleUserDetailsService userDetailsService;
	@Autowired
	TraineeRepository traineeRepository;
	@Autowired
	SalesManagerRepository salesManagerRepository;

	//private final UserRepository userRepository;

	public UserController(UserRepository userRepository, SingleUserDetailsService userDetailsService, 
			TraineeRepository traineeRepository, SalesManagerRepository salesManagerRepository) {
		super();
		this.userRepository = userRepository;
		this.userDetailsService = userDetailsService;
		this.traineeRepository = traineeRepository;
		this.salesManagerRepository = salesManagerRepository;
	}
// /users path to the list of users
	@RequestMapping("/users")
	public String getUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		System.out.println("Singfle user form controller --------------------------------------");
		System.out.println("userDetailsService.findUserFromCurrentSession();" + userDetailsService.findUserFromCurrentSession().toString());
		System.out.println("Singfle user form controller --------------------------------------");
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
		regService.register( user);
		return "redirect:/users";
		// redirect:/users -> after new user is created it redirects to path/users
	}
	
	@RequestMapping("/admin")
	public String testAdminRole(){return "admin";}
	
	@RequestMapping("/authorize")
	public String goTosetUserRole(Authentication authentication){
		if(userDetailsService.findUserFromCurrentSession().getRole().contains("USER"))return "auth/guest";
		return "redirect:/opportunities";
	}
	
	@PostMapping(value = "/authorize")
	public String setUserRole(ModelMap model, @RequestParam("code") int code) {
		SingleUser user = userDetailsService.findUserFromCurrentSession();
		user.setRoleByAuthCode(code);
		userRepository.save(user);
		if(code == 333) {
			Trainee trainee = new Trainee(user);
			traineeRepository.save(trainee);
			System.out.println("===== "+trainee.toString());
		}else if(code == 222) {
			SalesManager salesManager = new SalesManager(user);
			salesManagerRepository.save(salesManager);
			System.out.println("===== "+salesManager.toString());
		}
		return "redirect:/logout";
	}
	
	@GetMapping("/auth/change-password")
	public String changePasswordPage(@ModelAttribute("singleUser") SingleUser singleUser) {
			
			return "auth/changePassword";
		}
		
	@PostMapping("/auth/change-password")
	public String changePassword(@RequestParam("email") String email,@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
			Integer hashedOldPassword = oldPassword.hashCode();
			String hashedOldPasswordString = hashedOldPassword.toString();
			UserDetails userDetails = userDetailsService.loadUserByEmailForPasswordChange(email);
			SingleUserDetails singleUserDetails1 = (SingleUserDetails) userDetails;
			if(userDetails.getPassword().equals(hashedOldPasswordString)) {
			    singleUserDetails1.setPassword(newPassword);
			    userDetails = (UserDetails) singleUserDetails1;
			    userDetailsService.saveUserToDb(singleUserDetails1);
			    
			}else {
				System.out.println("password does not match");
			}
			return "redirect:/auth/login";	
	}
	
	@GetMapping("/account")
	public String viewDetails(@AuthenticationPrincipal SingleUserDetails loggedUser, Model model) {
		String email = loggedUser.getUsername();
		SingleUser user =  (SingleUser) userDetailsService.loadUserByUsername(email);
		model.addAttribute("user", user);
		return "redirect:/frontend/profile";
	}

}
