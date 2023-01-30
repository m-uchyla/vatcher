package com.fmdgroup.vatcher.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmdgroup.vatcher.model.SingleUser;

public interface UserRepository extends JpaRepository<SingleUser, Long> {
	

}
