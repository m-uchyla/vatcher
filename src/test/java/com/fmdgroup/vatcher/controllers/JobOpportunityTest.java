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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.boot.test.mock.mockito.MockBean;
	import org.springframework.test.context.ContextConfiguration;
	import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fmdgroup.vatcher.VatcherApplication;
	import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.Match;
import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;



	@SpringBootTest
	@AutoConfigureMockMvc
	@ContextConfiguration(classes = VatcherApplication.class)
	public class JobOpportunityTest {
		@MockBean 
		private JobOpportunityRepository repository ;
		
		@Autowired
		private MockMvc mockMvc;
		
		@Test
		public void test_createNewJobs() throws Exception{
			JobOpportunity jobOpportunity = new JobOpportunity("Job1", "Company1", "Location1", "Duration1", "Description1",
					new HashSet<>(List.of("Skill1", "Skill1.2")), null, null);
			// mockMvc is model view controller - it allows us to pretend requests to the server.
			mockMvc.perform(post("/addJobOpportunity")
					.flashAttr("jobOpportunity", jobOpportunity)) //zamiast .param jest .falshAttr
					.andExpect(status().is(302))
					.andExpect(view().name("redirect:/jobOpportunity"));
			
			verify(repository, times(1)).save(jobOpportunity);
			
		} 
		
		@Test 
		public void getJobs() throws Exception {
			List<JobOpportunity> jobOpportunitiesList = new ArrayList<>();
			jobOpportunitiesList.add(new JobOpportunity("Job1", "Company1", "Location1", "Duration1", "Description1",
			new HashSet<>(List.of("Skill1", "Skill1.2")), null, null));
			jobOpportunitiesList.add(new JobOpportunity("Job2", "Company2", "Location2", "Duration2", "Description2",
			new HashSet<>(List.of("Skill2", "Skill3")), null, null));
			when(repository.findAll()).thenReturn(jobOpportunitiesList);
						
			mockMvc.perform(get("/jobOpportunity"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("jobOpportunity", jobOpportunitiesList ))
			.andExpect(view().name("addUser")); //jak bedziemy mieli HTML to trzeba tutaj zmienić
			
		}
		

		@Test
		public void getActiveJObs() throws Exception{
			List<JobOpportunity> activeJobs = new ArrayList<>();
				activeJobs.add(new JobOpportunity("Job1", "Company1", "Location1", "Duration1", "Description1",
							new HashSet<>(List.of("Skill1", "Skill1.2")), null, null));
					activeJobs.add(new JobOpportunity("Job2", "Company2", "Location2", "Duration2", "Description2",
							new HashSet<>(List.of("Skill2", "Skill3")), null, null));
			
		when(repository.findByActiveTrue()).thenReturn(activeJobs);
		
		
			mockMvc.perform(MockMvcRequestBuilders.get("/activeJobOpportunities"))
		            .andExpect(status().isOk())
					.andExpect(model().attribute("jobOpportunity", activeJobs)) //to jest jak pobieram coś, tutaj to jest lista 
		            .andExpect(view().name("addUser"));
						
		}

		
		
		
		
	}
