package com.fmdgroup.vatcher.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fmdgroup.vatcher.model.Match;


public interface MatchRepository extends JpaRepository<Match, Long>{
	


}
