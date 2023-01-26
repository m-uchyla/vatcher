package com.fmdgroup.vatcher.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
		mockMvc.perform(post("/addUser").param("name","Filip")
				.param("emile", "eee@ss.pl" )
				.param("password","xxx"))
				.andExpect(status().isOk())
				.andExpect(view().name("singleUser"));
		
		verify(repository, times(1)).save(user);
		
	}
	
	
	
}
