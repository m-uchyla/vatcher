package com.fmdgroup.vatcher.controllers;

<<<<<<< Updated upstream
import javax.swing.JOptionPane;

=======
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;
import com.fmdgroup.vatcher.services.NotificationServiceImpl;

import javax.swing.JOptionPane; //create a pop-up dialog with buttons to confirm if the notification was read

@Controller
public class NotificationsController {
	private final NotificationsRepository notificationsRepository;
	
@Autowired
	private NotificationServiceImpl notificationServiceImpl;

	public NotificationsController(NotificationsRepository notificationsRepository) {
		super();
		this.notificationsRepository = notificationsRepository;
	}
	
	@PostMapping("/notifications")
	public String addNotification(@RequestBody Notifications notification) {
	notificationServiceImpl.addNotification(notification);
	return "notifications";
	}
	
	@GetMapping("/notifications")
	public String getAllNotifications(Model model) {
<<<<<<< Updated upstream
	model.addAttribute("notifications", notificationsRepository.findAll());
	return "notifications";
	}
	  
	@GetMapping("/userNotifications/{receiverId}")
	public String getNotifications(Model model, @PathVariable Long receiverId) {
	model.addAttribute("userNotifications", notificationServiceImpl.getNotifications(receiverId));
	return "userNotifications";
	}
	
	public void markAllAsRead(Model model) {
	int option = JOptionPane.showConfirmDialog(null, "Mark as read", "Read", JOptionPane.YES_NO_OPTION);
	      if (option == JOptionPane.YES_OPTION) {
	    	  model.addAttribute("notifications", notificationsRepository.findAll());
	    	  System.out.println("notifications");
	    }
	}
	
=======
		model.addAttribute("notifications", notificationsRepository.findAll());
		return "notifications";
	}
	
	@GetMapping("/userNotifications/{receiverId}")
	public String getNotifications(Model model, @PathVariable Long receiverId) {
		model.addAttribute("userNotifications", notificationServiceImpl.getNotifications(receiverId));
		return "userNotifications";
	}
	
	public void markAllAsRead(Model model) {
	int option = JOptionPane.showConfirmDialog(null, "Mark as read", "Read", JOptionPane.YES_NO_OPTION);
	      if (option == JOptionPane.YES_OPTION) {
	    	  model.addAttribute("notifications", notificationsRepository.findAll());
	    	  System.out.println("notifications");
	    }
	}
	
>>>>>>> Stashed changes
	
	
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
