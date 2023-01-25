package com.fmdgroup.vatcher.model;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class SalesManager extends User {
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private List<JobOpportunities> jobOpportunities;
		private String name;
		
		public SalesManager() {}
		
		public SalesManager(List <JobOpportunities> jobOpportunities, String name) {
			super();
			this.jobOpportunities = jobOpportunities;
			this.name = name;
			
		}
		
		
		public List<JobOpportunities> getJobopportunities() {
			return jobOpportunities;
		}

		public void setJobopportunities(List<JobOpportunities> jobOpportunities) {
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