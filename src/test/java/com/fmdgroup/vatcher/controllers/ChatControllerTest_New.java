package com.fmdgroup.vatcher.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.fmdgroup.vatcher.model.Chat;
import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.Notifications;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.ChatRepository;
import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;
import com.fmdgroup.vatcher.services.NotificationServiceImpl;
import com.fmdgroup.vatcher.services.SingleUserDetailsService;

@ExtendWith(MockitoExtension.class)
public class ChatControllerTest_New {


    @InjectMocks
    private ChatController chatController;

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private JobOpportunityRepository jobOpportunityRepository;

    @Mock
    private SingleUserDetailsService singleUserDetailsService;

    @Mock
    private Model model;
    
    @Mock
    private NotificationServiceImpl notificationServiceImpl;
    
    @Mock
    private Notifications notification;

    @Test
    public void testChatWithOpportunityID() {
        Long opportunityID = 1L;
        List<Chat> messages = new ArrayList<>();
        JobOpportunity jobOpportunity = new JobOpportunity();
        SingleUser user = new SingleUser();
        when(jobOpportunityRepository.findById(opportunityID)).thenReturn(java.util.Optional.of(jobOpportunity));
        when(chatRepository.findByOpportunityID(jobOpportunity)).thenReturn(messages);
        when(singleUserDetailsService.findUserFromCurrentSession()).thenReturn(user);
        String result = chatController.chatWithOpportunityID(opportunityID, model);
        verify(model).addAttribute("messages", messages);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("opportunityID", opportunityID);
        assertEquals("chat", result);
    }
    
    @Test
    void testPostMessage() {
      Long opportunityID = 1L;
      String content = "Test message";
      SingleUser user = new SingleUser();
      JobOpportunity jobOpportunity = new JobOpportunity();
      Chat messageChat = new Chat(user, content, jobOpportunity,new Date());
      List<Chat> messages = new ArrayList<>();
      messages.add(messageChat);
      
      when(singleUserDetailsService.findUserFromCurrentSession()).thenReturn(user);
      when(jobOpportunityRepository.findById(opportunityID)).thenReturn(java.util.Optional.of(jobOpportunity));
      when(chatRepository.findByOpportunityID(jobOpportunity)).thenReturn(messages);
      
      chatController.postMessage(content, opportunityID, model);
      
      verify(chatRepository).save(messageChat);
      verify(notificationServiceImpl).addNotification(any(Notifications.class));
    }
}
