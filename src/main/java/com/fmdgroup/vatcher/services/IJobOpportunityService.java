package com.fmdgroup.vatcher.services;

import java.util.List;

import org.springframework.ui.Model;

import com.fmdgroup.vatcher.model.JobOpportunity;

public interface IJobOpportunityService {

	List<JobOpportunity> getAllJobOpportunities() throws Exception;

	JobOpportunity findJobOpportunityByID(Long ID) throws Exception;

	JobOpportunity addJobOpportunity(JobOpportunity jobOpportunity) throws Exception;

	String getActiveJobOpportunities(Model model) throws Exception;

	List<JobOpportunity> getJobOpportunitiesBySalesManager(Long salesManagerId) throws Exception;

	JobOpportunity activateOrDeactivateJobOpportunity(Long ID, boolean active) throws Exception;

	JobOpportunity deactivateExpiredJobOpportunity(Long ID) throws Exception;

}
 