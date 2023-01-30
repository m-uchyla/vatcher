package com.fmdgroup.vatcher.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="single_users")
public class SingleUser {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String name;
private String email;
private String password;

public SingleUser() {}

public SingleUser(String name, String email, String password) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Override
public int hashCode() {
	return Objects.hash(email, name, password);
}



@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	SingleUser other = (SingleUser) obj;
	return Objects.equals(email, other.email) && Objects.equals(name, other.name)
			&& Objects.equals(password, other.password);
}

@Override
public String toString() {
	return "User [name=" + name + ", email=" + email + ", password=" + password + "]";
}

public Long getId() {
	// TODO Auto-generated method stub
	return id;
}

public void setId(long id) {
	this.id = id;
	
}





}
