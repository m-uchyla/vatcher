package com.fmdgroup.vatcher.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmdgroup.vatcher.model.Chat;
import com.fmdgroup.vatcher.model.JobOpportunity;


public interface ChatRepository extends JpaRepository<Chat, Long>{
	
	List<Chat> findByOpportunityID(JobOpportunity opportunityID);

}
