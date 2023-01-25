package com.fmdgroup.vatcher.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Trainee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Set<String> qualifications;
	private Set<String> jobsPreferences;
	
	@OneToOne(mappedBy = "users")
	private User user;
	
	public Trainee() {}
	
	public Trainee(Set<String> qualifications, Set<String> jobsPreferences, User user) {
		super();
		this.qualifications = qualifications;
		this.jobsPreferences = jobsPreferences;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Trainee [id=" + id + ", qualifications=" + qualifications + ", jobsPreferences=" + jobsPreferences
				+ ", user=" + user + "]";
	}
	
}
