package com.fmdgroup.vatcher.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Job_Opportunities")
@NamedQuery(name = "getAllJobs", query = "SELECT j FROM JobOpportunity j")
public class JobOpportunity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String jobTitle;
	private String company;
	private String location;
	private String duration;
	private String description;
	private HashSet<String> skills;
	@ManyToMany
	private Set<Trainee> applicants;
	@OneToMany
	private Set<Match> matches;
	
	public JobOpportunity() {}

	public JobOpportunity(String jobTitle, String company, String location, String duration, String description,
			HashSet<String> skills, Set<Trainee> applicants, Set<Match> matches) {
		super();
		this.jobTitle = jobTitle;
		this.company = company;
		this.location = location;
		this.duration = duration;
		this.description = description;
        this.skills = new HashSet<>(skills);
		this.applicants = applicants;
		this.matches = matches;
	}

	public Long getJob_id() {
		return job_id;
	}

	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HashSet<String> getSkills() {
		return skills;
	}

	public void setSkills(HashSet<String> skills) {
		this.skills = skills;
	}

public Set<Trainee> getApplicants() {
		return applicants;
	}

	public void setApplicants(Set<Trainee> applicants) {
		this.applicants = applicants;
	}

	public Set<Match> getMatches() {
		return matches;
	}

	public void setMatches(Set<Match> matches) {
		this.matches = matches;
	};
	
}
