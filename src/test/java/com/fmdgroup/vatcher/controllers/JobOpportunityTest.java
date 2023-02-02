package com.fmdgroup.vatcher.controllers;

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

import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;
import com.fmdgroup.vatcher.services.JobOpportunityService;



	@SpringBootTest
	@AutoConfigureMockMvc
	@ContextConfiguration(classes = VatcherApplication.class)
	public class JobOpportunityTest {
		@MockBean 
		private JobOpportunityRepository repository ;
		
		@MockBean
		private JobOpportunityService service;
		
		@Autowired
		private MockMvc mockMvc;
		
		//test for creating job opportunities
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
		
		
		// test for getting all jobs
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
		
		//test for finding active Jobs
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
		
//		@RequestMapping("/jobOpportunity{ID}")
//		public String findJobOpportunityByID(@PathVariable("id") Long ID, Model model) {
//			model.addAttribute("jobOpportunity", jobOpportunityRepository.findById(ID));
//			return "addUser";

		
		// test for finding specific job by its' ID 
		@Test
		public void findJobOpportnityById() throws Exception{
			JobOpportunity jobOpportunityById = new JobOpportunity("Job1", "Company1", "Location1", "Duration1", "Description1",
					new HashSet<>(List.of("Skill1", "Skill1.2")), null, null);
			
			when(service.findJobOpportunityByID(1L)).thenReturn(jobOpportunityById);
			
			mockMvc.perform(get("/jobOpportunity/1")) 	// 1 because I'm testing the jobopportunity ID 1
				.andExpect(status().isOk())
				.andExpect(model().attribute("jobOpportunity", jobOpportunityById))
				.andExpect(view().name("addUser"));
		}
		
		
		
	}
