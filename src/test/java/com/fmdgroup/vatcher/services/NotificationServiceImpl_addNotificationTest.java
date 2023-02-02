package com.fmdgroup.vatcher.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;

@RunWith(SpringRunner.class)

public class NotificationServiceImpl_addNotificationTest {

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;
    private NotificationsRepository notificationsRepository;

    @Test
    public void testAddNotification() {
      Notifications notification = new Notifications();
      
      // set properties of the notification object
      String newNotifications = notificationServiceImpl.addNotification(notification);
      assertEquals("notifications", newNotifications);
      String savedNotifications = notificationServiceImpl;
      
      // additional assertions to verify that the notification was saved correctly
      assertEquals(notification, savedNotifications);

    }
}
