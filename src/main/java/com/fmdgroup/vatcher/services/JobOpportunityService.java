
package com.fmdgroup.vatcher.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.fmdgroup.vatcher.model.JobOpportunity;
import com.fmdgroup.vatcher.repositories.JobOpportunityRepository;

@Service
public class JobOpportunityService implements IJobOpportunityService {
	@Autowired
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
	
	@Override
	public List<JobOpportunity> getJobOpportunitiesBySalesManager(Long salesManagerId) throws Exception {
		return jobRepository.findBySalesManager(salesManagerId);
	}

	public static List<JobOpportunity> findBySalesManager(Long salesManagerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//method that activates/deactivates a job offer
	@Override
	public JobOpportunity activateOrDeactivateJobOpportunity(Long ID, boolean active) throws Exception {
		JobOpportunity jobOpportunity = findJobOpportunityByID(ID);			//it takes the ID of jobopp and a boolean
		jobOpportunity.setActive(active);									//it retrieves the jobopp with the given ID
		return jobRepository.save(jobOpportunity);							// and sets it to the desired value (active or deactive)
	}
	//automatically deactivates a job offer if it has expired.
	@Override
	public JobOpportunity deactivateExpiredJobOpportunity(Long ID) throws Exception {
	  JobOpportunity jobOpportunity = findJobOpportunityByID(ID);
	  if (jobOpportunity.getExpirationDate().before(new Date())) {
	    jobOpportunity.setActive(false);
	    return jobRepository.save(jobOpportunity);
	  }
	  return jobOpportunity;
	}
	///@RequestParam  add to the method
	public List<JobOpportunity> getJobs( String company) {
		company="HSBC";
		return jobRepository.findByCompany(company);
        /*return jobRepository.findByJobTitleContainingAndCompanyContainingAndLocationContainingAndDurationContainingAndDescriptionContaining(
                filter.getJobTitle(), filter.getCompany(), filter.getLocation(),filter.getDuration(), filter.getDescription());*/
        }
	
	
	

	
	/*public void editJobOpportunityById(Long id) {
		 Optional<JobOpportunity> optJobOpportunity = jobRepository.findById(id);
		 if(optJobOpportunity.isPresent()) {
			 JobOpportunity jobOpportunity = optJobOpportunity.get();
			 
			 jobOpportunity.setActive(false);
			 jobOpportunity.setApplicants(null);
			 jobOpportunity.setCompany(null);
			 jobOpportunity.setDescription(null);
			 jobOpportunity.setDuration(null);
			 jobOpportunity.setExpirationDate(null);
			 jobOpportunity.setJobTitle(null);
			 jobOpportunity.setLocation(null);
			 jobOpportunity.setSkills(null);
			
			 
			 jobRepository.save(jobOpportunity);
		 }else {
			 System.out.println("job not found");
		 }
		
	}*/
}



