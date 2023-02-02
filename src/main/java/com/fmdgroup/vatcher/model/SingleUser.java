package com.fmdgroup.vatcher.model;
import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;


@Entity
@Table(name = "single_users")
public class SingleUser {

@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
//@NotEmpty(message = "Name should not be empty")
//@Size(min=2, max=50, message="Name length should be between 2 or 100 characters")
@Column(name="username")
private String name;
@Column(name="email")
private String email;
@Column(name="password")
private String password;
@Column(name="role")
private String role;




public SingleUser() {}


public SingleUser(String name, String email, String password) {
	System.out.println("Constructor/Creating A single user ");
	Integer hashedPassword = password.hashCode();
	String hashedPasswordString = hashedPassword.toString();
	this.name = name;
	this.email = email;
	this.password = hashedPasswordString;
	//this.password=password;
	
	
}

public SingleUser(String name, String email, String password,String role) {
	System.out.println("Constructor/Creating A single user ");
	Integer hashedPassword = password.hashCode();
	String hashedPasswordString = hashedPassword.toString();
	this.name = name;
	this.email = email;
	this.password = hashedPasswordString;
	//this.password=password;
	this.role=role;
	
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getRole() {
	
	return role;
}

public void setRole(String role) {
	this.role = role;
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
	return this.password;
}

public void setPassword(String password) {
	Integer hashedPassword = password.hashCode();
	String hashedPasswordString = hashedPassword.toString();
	this.password = hashedPasswordString;
	//this.password = password;
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
	return "SingleUser [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
			+ "]";
}


public void setRoleByAuthCode(int authCode) {
	
	if(authCode == 111) {
		this.setRole("ROLE_ADMIN");
	} else if(authCode==222){
		this.setRole("ROLE_SALESMANAGER");
	} else if(authCode==333) {
		this.setRole("ROLE_TRAINEE");
	}else {
		this.setRole("ROLE_USER");
	}
}



}
