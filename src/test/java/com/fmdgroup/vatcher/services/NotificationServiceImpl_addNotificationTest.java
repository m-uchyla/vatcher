package com.fmdgroup.vatcher.services;


import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fmdgroup.vatcher.controllers.NotificationsController;
import com.fmdgroup.vatcher.model.Notifications;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceImpl_addNotificationTest {
    @Mock
    private NotificationServiceImpl notificationServiceImpl;
    @InjectMocks
    private NotificationsController notificationsController;

    private MockMvc mockMvc;
    
    @Test
    public void addNotificationTest() throws Exception {
        Notifications notification = new Notifications();
        mockMvc = MockMvcBuilders.standaloneSetup(notificationsController).build();
        mockMvc.perform(post("/notifications")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
        verify(notificationServiceImpl).addNotification(notification);
    }
}
