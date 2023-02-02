package com.fmdgroup.vatcher.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmdgroup.vatcher.model.SingleUser;

public interface UserRepository extends JpaRepository<SingleUser, Long> {
	
	Optional<SingleUser> findByName(String name);
	Optional<SingleUser> findByEmail(String email);

}
