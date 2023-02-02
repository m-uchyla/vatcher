package com.fmdgroup.vatcher.model;


public class Chat {
	private SingleUser sender;
	private String content;
	private JobOpportunity opportunityID;
	
	public Chat() {
	}
	public Chat(SingleUser sender, String content, JobOpportunity opportunityID) {
		this.sender = sender;
		this.content = content;
		this.opportunityID = opportunityID;
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
	
	}

