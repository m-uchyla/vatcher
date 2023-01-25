package com.fmdgroup.vatcher.model;

	import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
	public class Notifications {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String message;
		private User sender;
		private User receiver;
		private Date timestamp;
		private boolean read;
		
		
		public Notifications() {
		}
		
		public Notifications(String message, User sender, User receiver, Date timestamp, boolean read) {
			super();
			this.message = message;
			this.sender = sender;
			this.receiver = receiver;
			this.timestamp = timestamp;
			this.read = read;
		}


		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public User getSender() {
			return sender;
		}

		public void setSender(User sender) {
			this.sender = sender;
		}

		public User getReceiver() {
			return receiver;
		}

		public void setReceiver(User receiver) {
			this.receiver = receiver;
		}

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

		public boolean isRead() {
			return read;
		}

		public void setRead(boolean read) {
			this.read = read;
		}
		
		
	}