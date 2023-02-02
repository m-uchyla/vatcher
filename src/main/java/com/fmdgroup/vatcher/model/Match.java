package com.fmdgroup.vatcher.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Match")
@NamedQuery(name = "getAllMatches", query = "SELECT j FROM Match j")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "trainee_id")
	private Trainee trainee;

	@ManyToOne
	@JoinColumn(name = "jobopportunity_id")
	private JobOpportunity jobopportunity;

	private String status;

	private Date matchedOn;

	public Match() {
	}

	public Match(Trainee trainee, JobOpportunity jobopportunity, String status, Date matchedOn) {
		super();
		this.trainee = trainee;
		this.jobopportunity = jobopportunity;
		this.status = status;
		this.matchedOn = matchedOn;
	}

	public Long getId() {
		return id;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public JobOpportunity getJobopportunity() {
		return jobopportunity;
	}

	public void setJobopportunity(JobOpportunity jobopportunity) {
		this.jobopportunity = jobopportunity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getMatchedOn() {
		return matchedOn;
	}

	public void setMatchedOn(Date matchedOn) {
		this.matchedOn = matchedOn;
	}

	@Override
	public String toString() {
		return "Match [status=" + status + ", matchedOn=" + matchedOn + ", getStatus()=" + getStatus()
				+ ", getMatchedOn()=" + getMatchedOn() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
