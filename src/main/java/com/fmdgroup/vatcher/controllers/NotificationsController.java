package com.fmdgroup.vatcher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmdgroup.vatcher.repositories.NotificationsRepository;

@Controller
public class NotificationsController {
	private final NotificationsRepository notificationsRepository;

	public NotificationsController(NotificationsRepository notificationsRepository) {
		super();
		this.notificationsRepository = notificationsRepository;
	}
	
	@RequestMapping("/notifications")
	public String getUsers(Model model) {
		model.addAttribute("notifications", notificationsRepository.findAll());
		return "notifications";
	}
}
