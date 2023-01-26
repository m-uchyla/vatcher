package com.fmdgroup.vatcher.model;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class SalesManager {
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}) 
		private List<JobOpportunity> jobOpportunities;

		@OneToOne
	    private SingleUser user;		//mapped by was deleted and it works IDK why xD
		
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
		
		public void setUser(SingleUser user) {		
			this.user = user;
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
			return "Sales Manager [id=" + id + ", Name=" + name + ", Jobopportunities=" + "]";
		}
		
	}