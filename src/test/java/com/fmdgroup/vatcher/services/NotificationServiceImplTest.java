package com.fmdgroup.vatcher.services;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.fmdgroup.vatcher.VatcherApplication;
import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;

@SpringBootTest
@ContextConfiguration(classes = VatcherApplication.class)
public class NotificationServiceImplTest {
  @Autowired
  private NotificationsRepository notificationsRepository;
  
  @Test
  public void testAddNotification() {
	SingleUser user1 = new SingleUser("User 1","user1@email.com","123", "ROLE_USER");
	user1.setId(1L);
    Notifications testNotification = new Notifications("Test message", user1, new Date(), false);
    notificationsRepository.save(testNotification);
/* 
 * For user1 who should get 1 message, check if on the list exists 1 record (expected 1, list.size())   
 */
    assertEquals(1, notifications.size());
/* 
 * For user1 who should get 1 message, check if both records (messages) are equal  [? obj1.equals(obj2)]
 */
    assertEquals(testNotification, notifications.get(0));
  }
  
  @Test
  public void testGetNotifications() {
    SingleUser user2 = new SingleUser("User 2","user1@email.com","123", "ROLE_USER");
    SingleUser user3 = new SingleUser("User 3","user2@email.com","456", "ROLE_ADMIN");
    user2.setId(2L);
    user3.setId(3L);
    notificationServiceImpl.addNotification(new Notifications("Test Message 1", user2, new Date(), false));
    notificationServiceImpl.addNotification(new Notifications("Test Message 2", user2, new Date(), false));
    notificationServiceImpl.addNotification(new Notifications("Test Message 3", user3, new Date(), false));
/* 
 * For user2(3) who should get 2(1) messages, check if on the list exist 2(1) records (expected 2(1), list.size())   
 */
    List<Notifications> user2Notifications = notificationServiceImpl.getNotifications(2L);
    assertEquals(2, user2Notifications.size());
    List<Notifications> user3Notifications = notificationServiceImpl.getNotifications(3L);
    assertEquals(1, user3Notifications.size());
  }
}