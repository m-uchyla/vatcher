package com.fmdgroup.vatcher.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.UserRepository;
import com.fmdgroup.vatcher.security.SingleUserDetails;
@Service
public class SingleUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;

	
	@Autowired
	public SingleUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
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

}
