package com.fmdgroup.vatcher.model;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalesManager extends User {
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private List<JobOpportunity> jobOpportunities;
		private String name;
		
		public SalesManager() {}
		
		public SalesManager(List <JobOpportunity> jobOpportunities, String name) {
			super();
			this.jobOpportunities = jobOpportunities;
			this.name = name;
			
		}
		
		
		public List<JobOpportunity> getJobopportunities() {
			return jobOpportunities;
		}

		public void setJobopportunities(List<JobOpportunity> jobOpportunities) {
			this.jobOpportunities = jobOpportunities;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SalesManager other = (SalesManager) obj;
			return Objects.equals(id, other.id);
		}

		@Override
		public String toString() {
			return "Sales Manager [id=" + id + ", Name=" + name + ", Jobopportunities=" + jobOpportunities + "]";
		}
		
	}