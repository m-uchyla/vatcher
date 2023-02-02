package com.fmdgroup.vatcher.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fmdgroup.vatcher.model.Chat;

@Controller
public class ChatController {
	private List<Chat> messages = new ArrayList<>();
	
	@GetMapping("/chat")		//maps to the chat page and returns all messages in the chat. adds all messages
								// to the model and returns the chat view
	public String chat(Model model) {
		model.addAttribute("messages", messages);
		return "chat";
	}	
	
	@GetMapping("/chat/{opportunityID}")	//maps to the chat page with a specific opportunityID and returns only
										//messages related to this opportunity
										//adds the filtered messages to the model and returns the chat view
	public String chatWithOpportunityID(@RequestParam("opportunityID") String opportunityID, Model model) {
		List<Chat> opportunityMessages = new ArrayList<>();
		for (Chat message : messages) {
			if (message.getOpportunityID().equals(opportunityID)) {
				opportunityMessages.add(message);
			}
		}
//			model.addAttribute("messages", opportunityMessages);
			model.addAttribute("opportunityID", opportunityID);
			return "chat";
	}
	
	@PostMapping("/chat/opportunity")		//maps to the chat page with a specific opportunitID and returns
											// only messages related to this opportunity
											//it adds the filtered messages to the model and returns the chat view
	public String postMessage(@RequestParam("sender") String sender, @RequestParam("content") String content, @RequestParam("opportunityID") String opportunityID, Model model) {
		messages.add(new Chat(sender, content, opportunityID));
		System.out.println(messages);
		return "redirect:/chat";
	
	}
	
										//allows to delete a specific message in the chat
										//filters the messages list and removes the matching messages
	@PostMapping("/delete")
	public String deleteMessage(@RequestParam("id") long id, Model model, RedirectAttributes redirectAttributes) {
	    // delete message logic here

	    redirectAttributes.addFlashAttribute("successMessage", "Message deleted successfully");
	    return "redirect:/chat";
	}
	
	//testowa metoda ktora mi pobierze wszystkie czaty 
}

