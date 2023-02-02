package com.fmdgroup.vatcher.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.services.SingleUserDetailsService;
@Component
public class SingleUserValidator implements Validator{
	private final SingleUserDetailsService singleUserDetailsService;
	
	@Autowired
	public SingleUserValidator(SingleUserDetailsService singleUserDetailsService) {
	
		this.singleUserDetailsService = singleUserDetailsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return SingleUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SingleUser singleUser = (SingleUser) target;
		try {
			singleUserDetailsService.loadUserByUsername(singleUser.getEmail());
		}catch(UsernameNotFoundException ignored) {
			return;
		}
		errors.rejectValue("name", "","user with this name exists");
		
	}

}
