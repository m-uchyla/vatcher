package com.fmdgroup.vatcher.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;

@Service
public class NotificationServiceImpl {
	
	@Autowired
		private NotificationsRepository notificationsRepository;
		private Notifications notification;

		public String addNotification(Notifications notification) {
			notificationsRepository.save(notification);
			return "notifications";
		}
		
		public List<Notifications> getAllNotifications() {
			return notificationsRepository.findAll();
		}

		public Notifications getNotifications(Long receiverId) {
			return notificationsRepository.findById(receiverId).get();
		}
		
//		public String getActiveNotifications(Model model) throws Exception {
//			List<Notifications> activeNotifications = notificationsRepository.findByActiveTrue();
//			model.addAttribute("actNotif", activeNotifications);
//			return "notifications";
//		}
		
		public void checkIfRead() {
//			return notification.isRead();
		}
}		