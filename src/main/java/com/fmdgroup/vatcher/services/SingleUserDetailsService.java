package com.fmdgroup.vatcher.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.UserRepository;
import com.fmdgroup.vatcher.security.SingleUserDetails;
@Service
public class SingleUserDetailsService implements UserDetailsService{
	@Autowired
	private  UserRepository userRepository;
	
	private  Authentication authentication;
	
	public SingleUserDetailsService() {};
	
	public SingleUserDetailsService(UserRepository userRepository,Authentication authentication) {
		this.userRepository = userRepository;
		this.authentication=authentication;
	}

	// load user by name
	/*@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Optional<SingleUser> singleUser = userRepository.findByName(name);
		System.out.println("SingleUserDetailsService class is working/ loadUserByUsername ");
		
		if (singleUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new SingleUserDetails(singleUser.get());
	}*/
	
	public List<SingleUser> getAllUsers(){
		return userRepository.findAll();
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<SingleUser> singleUser = userRepository.findByEmail(email);
		System.out.println("SingleUserDetailsService class is working/ loadUserByUsername ");
		
		if (singleUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new SingleUserDetails(singleUser.get());
	}
	
	
	public UserDetails loadUserByEmailForPasswordChange(String email) throws UsernameNotFoundException {
		Optional<SingleUser> singleUser = userRepository.findByEmail(email);

		if (singleUser.isPresent()) {
			return new SingleUserDetails(singleUser.get());
		}
		throw new UsernameNotFoundException("User not found");
	}

	public void saveUserToDb(SingleUserDetails singlUsDet) {
		userRepository.save(singlUsDet.getSingleUser());
	}
	
	
	 public void setAuthenticationObject (Authentication authentication2) {
    	 this.authentication = authentication2;
    }

   public Authentication getAuthenticationObject() {
	   return authentication;
   }
	
	public SingleUser findUserFromCurrentSession() {
		String email = getAuthenticationObject().getName();
		Optional<SingleUser> singleUser = userRepository.findByEmail(email);
		
		if (singleUser.isPresent()) {
			UserDetails userDetails =  new SingleUserDetails(singleUser.get());
			SingleUserDetails singleUserDetails1 = (SingleUserDetails) userDetails;
			return singleUserDetails1.getSingleUser();
		}
		
		throw new UsernameNotFoundException("User not found");
		
	}
	
		/*public SingleUser findUserFromCurrentSession(String email) {
			Optional<SingleUser> singleUser = userRepository.findByEmail(email);
			
			if (singleUser.isPresent()) {
				UserDetails userDetails =  new SingleUserDetails(singleUser.get());
				SingleUserDetails singleUserDetails1 = (SingleUserDetails) userDetails;
				return singleUserDetails1.getSingleUser();
			}
			
			throw new UsernameNotFoundException("User not found");
			
		}*/
	
	
	       
	
}
