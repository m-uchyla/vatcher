package com.fmdgroup.vatcher.model;


public class Chat {
	private String sender;
	private String content;
	private String opportunityID;
	
	public Chat() {
	}
	public Chat(String sender, String content, String opportunityID) {
		this.sender = sender;
		this.content = content;
		this.opportunityID = opportunityID;
	}
			
		
		

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getOpportunityID() {
		return opportunityID;
	}
	public void setOpportunityID(String opportunityID) {
		this.opportunityID = opportunityID;
	}
	
	}

