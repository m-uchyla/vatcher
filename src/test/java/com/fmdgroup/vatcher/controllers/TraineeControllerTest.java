package com.fmdgroup.vatcher.controllers;


import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;


import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;



import com.fmdgroup.vatcher.VatcherApplication;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.TraineeRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;

import com.fmdgroup.vatcher.services.TraineeService;

@EnableWebMvc
@SpringBootTest
	@AutoConfigureMockMvc
	@ContextConfiguration(classes = VatcherApplication.class)
	public class TraineeControllerTest {
	@MockBean
	private TraineeService service;
		@MockBean 
		private TraineeRepository repository ;
		@MockBean
	    private UserRepository userRepository;
		
		@Autowired
		private MockMvc mockMvc;
		
		@Test
		public void test_updateQualification() throws Exception{
			ArgumentCaptor<Trainee> mockTraineeCaptor = null;
		
	        Set<String> newQualifications = new HashSet<>();
	        newQualifications.add("yyy");
	
	        

	    	Trainee trainee = new Trainee();
	    	trainee.setQualifications(newQualifications);
	    	
	    	when(service.traineeQualification()).thenReturn(newQualifications);
	    	when(repository.save(any(Trainee.class))).thenReturn(trainee);
	    	
	    	mockMvc.perform(post("/addQualification/{id}", 1).param("qualification", "yyy"))
			.andExpect(status().isOk())
			.andExpect(view().name("singleUser"));
	     
	    	verify(service, times(1)).traineeQualification();
		}

		
		//@Test
//		public void test_SaveTrainee() throws Exception {
//			
//			Set<String> jobPreferences = new HashSet<>();
//		    Set<String> jobQualifications = new HashSet<>();
//		    SingleUser user = new SingleUser();
//		    user.setId(1L);
//		    Trainee trainee = new Trainee(jobQualifications, jobPreferences, user);
//		    
//		    //when(service.addTrainee(any(Trainee.class))).thenReturn("singleUser");
//
//		    mockMvc.perform(MockMvcRequestBuilders.post("/savetrainees")
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .content(new ObjectMapper().writeValueAsString(trainee)))
//            .andExpect(MockMvcResultMatchers.status().is(404))
//            .andExpect(view().name("singleUser"));
//		    
//		}

		@Test
		public void test_AddTrainee() throws Exception  {
			Set<String> qualifications = new HashSet<>();
			Set<String> jobsPreferences = new HashSet<>();
			SingleUser user = new SingleUser("jax","xx","zxz");
		    Trainee trainee = new Trainee(qualifications,jobsPreferences,user);
		    //trainee.setId(1L);

		    mockMvc.perform(post("/addtrainee")
		    		.flashAttr("trainee", trainee))
			.andExpect(status().isOk())
			.andExpect(view().name("singleUser"));
	
	verify(service, times(1)).addTrainee(trainee);
		}
//

		
		}




