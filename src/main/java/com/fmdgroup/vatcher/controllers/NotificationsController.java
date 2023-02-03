package com.fmdgroup.vatcher.controllers;

import javax.swing.JOptionPane;

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
	
@Autowired
	private NotificationServiceImpl notificationServiceImpl;
//	private Notifications notification;

	public NotificationsController(NotificationsRepository notificationsRepository) {
		super();
		this.notificationsRepository = notificationsRepository;
//		this.notification = notification;
	}
	
	@PostMapping("/notifications")
	public String addNotification(@RequestBody Notifications notification) {
	notificationServiceImpl.addNotification(notification);
	return "notifications";
	}
	
	@GetMapping("/notifications")
	public String getAllNotifications(Model model) {
	model.addAttribute("notifications", notificationsRepository.findAll());
	return "notifications";
	}
	  
	@GetMapping("/userNotifications/{receiverId}")
	public String getNotifications(Model model, @PathVariable Long receiverId) {
	model.addAttribute("userNotifications", notificationServiceImpl.getNotifications(receiverId));
	return "userNotifications";
	}
	
//	@RequestMapping("/activeNotifications")
//	public String getActiveNotifications(Model model) {
//	model.addAttribute("actNotif", notificationsRepository.findByActiveTrue());
//	return "activeNotifications";
//	}
	
//	@GetMapping("/notifications")
	public void checkIfRead(@PathVariable Long receiverId) {
	int option = JOptionPane.showConfirmDialog(null, "Mark as read", "Read", JOptionPane.YES_NO_OPTION);
	      if (option == JOptionPane.YES_OPTION && notificationServiceImpl.getNotifications(receiverId).isRead()==false) {
	    	  notificationServiceImpl.getNotifications(receiverId).setRead(true);
	      }
	}
	
	
//	@PostMapping("/newMessageNotifications")
//	public void notifyNewMessage(Long receiverId, String message) {
//	SingleUser receiver = userRepository.findById(receiverId).get();
//	Notifications notificationMessage = new Notifications(message, receiver, new Date(), false);
//	notificationsRepository.save(notificationMessage);
//	}
//	
//	@PostMapping("/newJobNotifications")
//	public void notifyNewJobOpportunity(Long receiverId, String jobOpportunity) {
//	SingleUser receiver = userRepository.findById(receiverId).get();
//	Notifications notificationNewJobOpportunity = new Notifications(jobOpportunity, receiver, new Date(), false);
//	notificationsRepository.save(notificationNewJobOpportunity);
//	}
}
