package com.fmdgroup.vatcher.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;

@Service
public class NotificationServiceImpl {
	@Autowired
	
	private NotificationsRepository notificationsRepository;

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
		
//		public Notifications checkIfRead(boolean read) {
//			return notificationsRepository.;
//		}
}		