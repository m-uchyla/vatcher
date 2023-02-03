package com.fmdgroup.vatcher.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private SingleUser sender;
	private String content;
	
	@OneToOne
	private JobOpportunity opportunityID;
	private Date timestamp;
	
	public Chat() {
	}
	public Chat(SingleUser sender, String content, JobOpportunity opportunityID, Date timestamp) {
		this.sender = sender;
		this.content = content;
		this.opportunityID = opportunityID;
		this.timestamp = timestamp;
	}	

	public SingleUser getSender() {
		return sender;
	}

	public void setSender(SingleUser sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public JobOpportunity getOpportunityID() {
		return opportunityID;
	}
	public void setOpportunityID(JobOpportunity opportunityID) {
		this.opportunityID = opportunityID;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Chat [id=" + id + ", sender=" + sender + ", content=" + content + ", opportunityID=" + opportunityID
				+ ", timestamp=" + timestamp + "]";
	}
}

