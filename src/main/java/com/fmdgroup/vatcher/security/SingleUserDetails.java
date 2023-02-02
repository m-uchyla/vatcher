package com.fmdgroup.vatcher.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fmdgroup.vatcher.model.SingleUser;

public class SingleUserDetails implements UserDetails{
	private final SingleUser singleUser;
	
	
	

	public SingleUserDetails(SingleUser singleUser) {
		this.singleUser = singleUser;
	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(singleUser.getRole()));	}

	@Override
	public String getPassword() {
		System.out.println("Single user details / getPassword");
		return this.singleUser.getPassword();
	}
	
	public void setPassword(String password) {
        this.singleUser.setPassword(password);
    }

	@Override
	public String getUsername() {
		
		return this.singleUser.getName();
	}
	
	public String getEmail() {
		return this.singleUser.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	// need for receiving data of authenticated user
	public SingleUser getSingleUser() {
		return this.singleUser;
	}
	
     
}
