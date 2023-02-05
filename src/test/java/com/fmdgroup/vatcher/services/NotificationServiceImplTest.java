package com.fmdgroup.vatcher.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceImplTest {

    @InjectMocks
    private NotificationServiceImpl notificationServiceImpl;

    @Mock
    private NotificationsRepository notificationsRepository;

    @Test
    public void testAddNotification() {
        Notifications notification = new Notifications();
        when(notificationsRepository.save(notification)).thenReturn(notification);
        String addedNotification = notificationServiceImpl.addNotification(notification);
        verify(notificationsRepository).save(notification);
        assertEquals("notifications", addedNotification);
    }
    
    @Test
    public void testGetAllNotifications() {
        List<Notifications> notifications = new ArrayList<>();
        notifications.add(new Notifications());
        notifications.add(new Notifications());
        when(notificationsRepository.findAll()).thenReturn(notifications);
        List<Notifications> gotNotifications = notificationServiceImpl.getAllNotifications();
        verify(notificationsRepository).findAll();
        assertEquals(notifications, gotNotifications);
    }
    
    @Test
    public void testGetNotifications() {
        Long receiverId = 1L;
        Notifications notification = new Notifications();
        Optional<Notifications> notificationOptional = Optional.of(notification);
        when(notificationsRepository.findById(receiverId)).thenReturn(notificationOptional);
        Notifications gotMyNotification = notificationServiceImpl.getNotifications(receiverId);
        verify(notificationsRepository).findById(receiverId);
        assertEquals(notification, gotMyNotification);
    }
    
}
