package com.fmdgroup.vatcher.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fmdgroup.vatcher.model.Chat;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.ChatRepository;
import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;
import com.fmdgroup.vatcher.services.SingleUserDetailsService;

@Controller
public class ChatController {
	private List<Chat> messages = new ArrayList<>();
	@Autowired
	SingleUserDetailsService userDetailsService;
	@Autowired
	JobOpportunityRepository jobOpportunityRepository;
	@Autowired
	ChatRepository chatRepository;
	
//	@GetMapping("/chat")		//maps to the chat page and returns all messages in the chat. adds all messages
//								// to the model and returns the chat view
//	public String chat(Model model) {
//		model.addAttribute("messages", messages);
//		model.addAttribute("user", userDetailsService.findUserFromCurrentSession());
//		return "chat";
//	}	
	
	@GetMapping("/chat")
	public String chatWithOpportunityID(@RequestParam("id") Long opportunityID, Model model) {
		model.addAttribute("messages", chatRepository.findByOpportunityID(jobOpportunityRepository.findById(opportunityID).get()));
		model.addAttribute("user", userDetailsService.findUserFromCurrentSession());
		model.addAttribute("opportunityID", opportunityID);
		return "chat";
	}
	
	@PostMapping("/chat/send")		//maps to the chat page with a specific opportunitID and returns
									// only messages related to this opportunity
									//it adds the filtered messages to the model and returns the chat view
	public String postMessage(
			@RequestParam("content") String content, 
			@RequestParam("opportunityID") Long opportunityID
			,Model model) {
		Chat messageChat = new Chat(
				userDetailsService.findUserFromCurrentSession(), 
				content, 
				jobOpportunityRepository.findById(opportunityID).get(),
				new Date());
		messages.add(messageChat);
		chatRepository.save(messageChat);
		System.out.println(messages);
		return "redirect:/chat/?id="+opportunityID;
	
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

