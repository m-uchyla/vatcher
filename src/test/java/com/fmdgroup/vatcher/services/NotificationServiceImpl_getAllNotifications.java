package com.fmdgroup.vatcher.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceImpl_getAllNotifications {

    @InjectMocks
    private NotificationServiceImpl notificationServiceImpl;

    @Mock
    private NotificationsRepository notificationsRepository;

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
}