package com.fmdgroup.vatcher.services;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.repositories.UserRepository;


@Service
public class RegistrationService {
	

	private final UserRepository userRepository;

	@Autowired
	public RegistrationService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void register(SingleUser singleUser) {
		//singleUser.setRole("ROLE_USER");
		singleUser.setRoleByAuthCode(111);
		userRepository.save(singleUser);
	}
	

}
