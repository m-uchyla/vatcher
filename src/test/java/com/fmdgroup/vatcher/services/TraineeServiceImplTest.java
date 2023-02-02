package com.fmdgroup.vatcher.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fmdgroup.vatcher.VatcherApplication;
import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.model.Trainee;
import com.fmdgroup.vatcher.repositories.TraineeRepository;

import static org.mockito.ArgumentMatchers.anyLong;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = VatcherApplication.class)
public class TraineeServiceImplTest {

    @MockBean
    private TraineeRepository traineeRepository;

    @MockBean
    private JobOpportunityService jobOpportunityService;

    @MockBean
    private TraineeService traineeService;
    

    @Test
    public void testGetJobOpportunities() throws Exception {
        Trainee trainee = new Trainee();
        Set<JobOpportunity> jobOpportunities = new HashSet<>();
        jobOpportunities.add(new JobOpportunity("Software Engineer", "Google", "Mountain View, CA", null, null, null, null));
        trainee.setJobOpportunities(jobOpportunities);
        Long traineeId = 1L;
        when(traineeService.getJobOpportunities(traineeId)).thenReturn(jobOpportunities);

        Set<JobOpportunity> result = traineeService.getJobOpportunities(traineeId);
        assertEquals(jobOpportunities, result);

        verify(traineeService).getJobOpportunities(traineeId);
    }

    @Test
    public void testGetNotAppliedJobOpportunities() throws Exception {
        Trainee trainee = new Trainee();
        JobOpportunity jobOpportunity = new JobOpportunity("Software Engineer", "Google", "Mountain View, CA", null, null, null, null);
        Set<JobOpportunity> appliedJobs = new HashSet<>();
        appliedJobs.add(jobOpportunity);
        trainee.setJobOpportunities(appliedJobs);

        List<JobOpportunity> allJobOpportunities = new ArrayList<>();
        allJobOpportunities.add(new JobOpportunity("Software Engineer", "Google", "Mountain View, CA", null, null, null, null));
        allJobOpportunities.add(new JobOpportunity("Data Scientist", "Facebook", "Menlo Park, CA", null, null, null, null));
        when(traineeService.getNotAppliedJobOpportunities(trainee)).thenReturn(
        		allJobOpportunities.stream().filter(job -> !appliedJobs.contains(job))
	            .collect(Collectors.toSet())
        );

        Set<JobOpportunity> result = traineeService.getNotAppliedJobOpportunities(trainee);
        Set<JobOpportunity> expectedResult = allJobOpportunities.stream()
            .filter(job -> !appliedJobs.contains(job))
            .collect(Collectors.toSet());
        assertEquals(expectedResult, result);

        verify(traineeService).getNotAppliedJobOpportunities(trainee);
    }
}
