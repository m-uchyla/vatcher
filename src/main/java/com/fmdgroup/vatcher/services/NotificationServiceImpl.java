package com.fmdgroup.vatcher.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fmdgroup.vatcher.model.Notifications;

@Service
public class NotificationServiceImpl {
  private List<Notifications> notifications = new ArrayList<>();
  
  public void addNotification(Notifications notification) {
    notifications.add(notification);
  }
  
  public List<Notifications> getNotifications(Long receiverId) {
    List<Notifications> result = new ArrayList<>();
    for (Notifications notification : notifications) {
      if (notification.getReceiver().getId().equals(receiverId)) {
        result.add(notification);
      }
    }
    return result;
  }
}