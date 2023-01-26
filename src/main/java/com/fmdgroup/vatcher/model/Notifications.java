package com.fmdgroup.vatcher.model;

	import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

	@Entity
	public class Notifications {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String message;
		@ManyToOne
		private SingleUser sender;
		@ManyToOne
		private SingleUser receiver;
		private Date timestamp;
		private boolean read;
		
		
		public Notifications() {
		}
		
		public Notifications(String message, SingleUser sender, SingleUser receiver, Date timestamp, boolean read) {
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

		public SingleUser getSender() {
			return sender;
		}

		public void setSender(SingleUser sender) {
			this.sender = sender;
		}

		public SingleUser getReceiver() {
			return receiver;
		}

		public void setReceiver(SingleUser receiver) {
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
		
		@Override
		public int hashCode() {
			return Objects.hash(message, sender, receiver);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Notifications other = (Notifications) obj;
			return Objects.equals(id, other.id);
		}

		@Override
		public String toString() {
			return "Notifications [message=" + message + ", sender=" +  ", receiver="
					+ ", timestamp=" + timestamp + ", read=" + read + "]";
		}
		
	}