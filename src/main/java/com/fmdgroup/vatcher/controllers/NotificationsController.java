package com.fmdgroup.vatcher.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;
import com.fmdgroup.vatcher.services.NotificationServiceImpl;

@Controller
public class NotificationsController {
	private final NotificationsRepository notificationsRepository;

	public NotificationsController(NotificationsRepository notificationsRepository) {
		super();
		this.notificationsRepository = notificationsRepository;
	}
	
	@RequestMapping("/notifications")
	public String getNotifications(Model model) {
		model.addAttribute("notifications", notificationsRepository.findAll());
		return "addUser";
	}
	
	@Autowired
	private NotificationServiceImpl notificationServiceImpl;
	
	@PostMapping
	public void addNotification(@RequestBody Notifications notification) {
	notificationServiceImpl.addNotification(notification);
	}
	  
	@GetMapping("/{receiverId}")
	public List<Notifications> getNotifications(@PathVariable Long receiverId) {
	return notificationServiceImpl.getNotifications(receiverId);
	}
}
