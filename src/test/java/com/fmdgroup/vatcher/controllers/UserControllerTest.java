package com.fmdgroup.vatcher.controllers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import com.fmdgroup.vatcher.VatcherApplication;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.UserRepository;



@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = VatcherApplication.class)
public class UserControllerTest {
	@MockBean 
	private UserRepository repository ;
	 
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_createNewUser() throws Exception{
		SingleUser user = new SingleUser("Filip","eee@ss.pl","xxx");
		// mockMvc is model view controller - it allows us to pretend requests to the server.
		mockMvc.perform(post("/addUser").param("name","Filip")
				.param("email", "eee@ss.pl" )
				.param("password","xxx"))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/users"));
		
		verify(repository, times(1)).save(user);
		
	}
	
	@Test 
	public void test_getUsers() throws Exception {
		List<SingleUser> usersList = new ArrayList<>();
		usersList.add(new SingleUser("Adam","adam@adam.pl","12345"));
		when(repository.findAll()).thenReturn(usersList); 
		
		// "/users" below is the path from @RequestMapping for getUsers method.
		
		mockMvc.perform(get("/users"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("users", usersList ))
		.andExpect(view().name("singleUser"));
		
	}
	
	
	
	
	
}
