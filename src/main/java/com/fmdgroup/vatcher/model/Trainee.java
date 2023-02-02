package com.fmdgroup.vatcher.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.JoinTable;

@Entity
@Table(name="trainees")
public class Trainee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ElementCollection
	private Set<String> qualifications = new HashSet<String>();
	@ElementCollection
	private Set<String> jobsPreferences = new HashSet<String>();
	@OneToOne
	private SingleUser user;
	@ManyToMany
	@JoinTable(name = "trainees_jobOpportunities", joinColumns = @JoinColumn(name = "Job_Opportunities_id"),
	inverseJoinColumns = @JoinColumn(name = "trainees_id"))
	private Set<JobOpportunity> jobOpportunities;
	@ManyToOne
	private Match match;
	
	// this is for retrieving job offers applied by the trainee user:
	@OneToMany(mappedBy = "trainee")
	private Set<JobOpportunity> jobOpportunity = new HashSet<>();	

	public Trainee() {}
	
	public Trainee(SingleUser user) {
		super();
		this.user = user;
	}
	
	public Trainee(Set<String> qualifications, Set<String> jobsPreferences, SingleUser user) {
		super();
		this.qualifications = qualifications;
		this.jobsPreferences = jobsPreferences;
		this.user = user;
	}
	
	public Set<JobOpportunity> getJobOpportunities() {
		return jobOpportunities;
	}

	public void setJobOpportunities(Set<JobOpportunity> jobOpportunities) {
		this.jobOpportunities = jobOpportunities;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<String> getQualifications() {
		return qualifications;
	}

	public void setQualifications(Set<String> qualifications) {
		this.qualifications = qualifications;
	}

	public Set<String> getJobsPreferences() {
		return jobsPreferences;
	}

	public void setJobsPreferences(Set<String> jobsPreferences) {
		this.jobsPreferences = jobsPreferences;
	}

	public SingleUser getUser() {
		return user;
	}

	public void setUser(SingleUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Trainee [id=" + id + ", qualifications=" + qualifications + ", jobsPreferences=" + jobsPreferences
				+ ", user=" + user + "]";
	}
	//this is for retrieving job offers the trainee did NOT apply for:
	public Set<String> getAppliedJobTitles() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
