package com.fmdgroup.vatcher.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceImpl_addNotificationTest {

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
}
