package com.fmdgroup.vatcher.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fmdgroup.vatcher.VatcherApplication;
import com.fmdgroup.vatcher.model.Chat;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = VatcherApplication.class)
class ChatControllerTest {

	@InjectMocks
private ChatController chatController;
	@Mock
	private Model model;

	private List<Chat> messages;
	private Chat message;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		messages = new ArrayList<>();
		message = new Chat("sender", "content", "opportunityID");
		messages.add(message);
	}

	@Test
	public void testChat() {
	    List<Message> messages = new ArrayList<Message>();
		when(model.addAttribute("messages", messages)).thenReturn(model);
		String viewName = chatController.chat(model);
		assertEquals("chat", viewName);
		verify(model, times(1)).addAttribute("messages", messages);
	}
		@Test
		public void testChatWithOpportunityID() {
		  Chat chat = new Chat();
		  List<Chat> messages = Collections.singletonList(chat);
		  String result = chatController.chatWithOpportunityID("opportunityID", model);
		  assertEquals("chat", result);
	//	  verify(model).addAttribute("messages", messages);
		  verify(model).addAttribute("opportunityID", "opportunityID");
		} 
		
		
//		List<Chat> opportunityMessages = new ArrayList<>();
//		opportunityMessages.add(message);
//		when(model.addAttribute("messages", opportunityMessages)).thenReturn(model);
//		when(model.addAttribute("opportunityID", opportunityID)).thenReturn(model);
//		String viewName = chatController.chatWithOpportunityID(opportunityID, model);
//		assertEquals("chat", viewName);
//		verify(model, times(1)).addAttribute("messages", opportunityMessages);
//		verify(model, times(1)).addAttribute("opportunityID", opportunityID);
//	}

	@Test
	public void testPostMessage() {
		String sender = "sender";
		String content = "content";
		String opportunityID = "opportunityID";
		String viewName = chatController.postMessage(sender, content, opportunityID, model);
		assertEquals("redirect:/chat", viewName);
		Chat addedMessage = messages.get(messages.size() - 1);
		assertEquals(sender, addedMessage.getSender());
		assertEquals(content, addedMessage.getContent());
		assertEquals(opportunityID, addedMessage.getOpportunityID());
	}


	@Test
	public void testDeleteMessage() {
	    ChatController chatController = new ChatController();
	    Model model = mock(Model.class);
	    RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

	    String result = chatController.deleteMessage(1L, model, redirectAttributes);

	    assertEquals("redirect:/chat", result);
	    verify(redirectAttributes).addFlashAttribute("successMessage", "Message deleted successfully");
	}
	}