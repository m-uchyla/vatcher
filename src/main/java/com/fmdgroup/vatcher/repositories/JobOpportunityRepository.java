package com.fmdgroup.vatcher.repositories;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fmdgroup.vatcher.model.JobOpportunity;

public interface JobOpportunityRepository extends JpaRepository<JobOpportunity ,Long> {
	
	List<JobOpportunity> findByActiveTrue();	//for active jobopportunities

	List<JobOpportunity> findByJobTitleContainingAndCompanyContainingAndLocationContainingAndDurationContainingAndDescriptionContaining(
			String jobTitle, String company, String location, String duration, String description);

	List<JobOpportunity> findBySalesManager(Long salesManagerId);
	
	List<JobOpportunity> findByCompany(String company);

	
 

}
