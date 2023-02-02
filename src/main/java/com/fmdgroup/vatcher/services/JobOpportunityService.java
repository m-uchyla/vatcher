package com.fmdgroup.vatcher.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;

@Service
public class JobOpportunityService implements IJobOpportunityService {
	
	private JobOpportunityRepository jobRepository;
	
	


	@Override
	public List<JobOpportunity> getAllJobOpportunities() throws Exception{
		return jobRepository.findAll();
	}
	
	//or written like this:
//	public List<JobOpportunity> getAllJobOpportunities() throws Exception {
//		List<JobOpportunity> jobOpportunities = jobRepository.findAll();
//		if(jobOpportunities == null) {
//			throw new Exception();
//		}
//		return jobOpportunities;
//	}



	@Override
	public JobOpportunity addJobOpportunity(JobOpportunity jobOpportunity) throws Exception{
		return jobRepository.save(jobOpportunity);
	}


	@Override
	public String getActiveJobOpportunities(Model model) throws Exception {
		List<JobOpportunity> activeJobOpportunities = jobRepository.findByActiveTrue();
		model.addAttribute("jobOpportunities", activeJobOpportunities);
		return "addUser";
	}
	
	@Override
	public JobOpportunity findJobOpportunityByID(Long ID) throws Exception {
		Optional<JobOpportunity> optJobOpportunity = jobRepository.findById(ID);
		return optJobOpportunity.orElseThrow(()-> new Exception());
	}
	
	

}
