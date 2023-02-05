package com.fmdgroup.vatcher.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.repositories.NotificationsRepository;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceImpl_getNotification {

    @InjectMocks
    private NotificationServiceImpl notificationServiceImpl;

    @Mock
    private NotificationsRepository notificationsRepository;

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