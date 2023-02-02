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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import com.fmdgroup.vatcher.VatcherApplication;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.UserRepository;
import com.fmdgroup.vatcher.services.RegistrationService;
import com.fmdgroup.vatcher.services.SingleUserDetailsService;
import com.fmdgroup.vatcher.services.TraineeService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@ContextConfiguration(classes = VatcherApplication.class)


public class UserControllerTest {
	@MockBean 
	private UserRepository repository ;
	@MockBean
	private SingleUserDetailsService service;
	@Autowired
	private WebApplicationContext context;
	@MockBean
	private RegistrationService regService;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	HttpSecurity http;

	
	@Test
	@WithMockUser
    public void test_createNewUser() throws Exception {
		
        SingleUser user = new SingleUser("ff","ss","sfe","sasfas");

        mockMvc.perform(post("/addUser")
                .param("name", "ff")
                .param("email", "ss")
                .param("password", "sfe"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users"));

        verify(regService, times(1)).register(user);

    }
	
	@Test 
	@WithMockUser
	public void test_getUsers() throws Exception {
		List<SingleUser> usersList = new ArrayList<>();
		SingleUser user1 = new SingleUser("Adam","adam@adam.pl","12345");
		SingleUser user2 = new SingleUser("Adamw","adam@adawm.pl","12345w");
		//usersList.add(new SingleUser("Adam","adam@adam.pl","12345"));
		//usersList.add(new SingleUser("Adam","adam@adam.pl","12345"));
		usersList.add(user1);
		usersList.add(user2);
		when(service.getAllUsers()).thenReturn(usersList); 

		mockMvc.perform(get("/users"))
		//.andExpect(model().attribute("users", usersList))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/users"));
		
		verify(service, times(1)).getAllUsers();
	}
	
	
	
	
	
}
