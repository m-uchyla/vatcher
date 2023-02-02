package com.fmdgroup.vatcher.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmdgroup.vatcher.model.SingleUser;
import com.fmdgroup.vatcher.model.Trainee;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {
	
	Optional<Trainee> findByUser(SingleUser user);
	
}
