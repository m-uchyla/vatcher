package com.fmdgroup.vatcher.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fmdgroup.vatcher.model.JobOpportunity;

public interface JobOpportunityRepository extends JpaRepository<JobOpportunity ,Long> {
	
	List<JobOpportunity> findByActiveTrue();	//for active jobopportunities
	List<JobOpportunity> findBySalesManager(Long salesManagerId);
}
