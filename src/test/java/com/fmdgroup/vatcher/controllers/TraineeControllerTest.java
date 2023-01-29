package com.fmdgroup.vatcher.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fmdgroup.vatcher.VatcherApplication;
import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.TraineeRepository;
import com.fmdgroup.vatcher.repositories.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = VatcherApplication.class)
public class TraineeControllerTest {

	@MockBean 
	private TraineeRepository repository ;
	
	@MockBean
    private UserRepository userRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void test_SaveTrainee() throws Exception {
		
		 Trainee trainee = new Trainee();
		    trainee.setId(1L);
		    SingleUser user = new SingleUser();
		    user.setId(1L);
		    trainee.setUser(user);
		    
		    // Mock the findById method of userRepository to return the user object
		    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		    
		    // Mock the save method of traineeRepository to return the trainee object
		    when(repository.save(trainee)).thenReturn(trainee);

           
        mockMvc.perform(post("/trainees")
        		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
        		.param("jobPreferences", trainee.getJobsPreferences())
        		.param("qualification", trainee.getQualifications())
        		.param("userId", trainee.getUser().getId()))
        		.andExpect(status().isOk());
	

       
        
        verify(userRepository, times(1)).findById(1L);

       
        verify(repository, times(1)).save(trainee);
    }


}
