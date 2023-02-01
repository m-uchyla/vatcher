package com.fmdgroup.vatcher.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmdgroup.vatcher.model.Match;
import com.fmdgroup.vatcher.repositories.MatchRepository;
@Service
public class MatchService implements IMatchService{
	@Autowired
	private MatchRepository matchRepository;
	
	@Override
	public void createNewMatch(Match match) throws Exception {
		matchRepository.save(match);
		
	}

	@Override
	public List<Match> findAllMatches() {
		return matchRepository.findAll();
	}

	@Override
	public void deleteMatch(Long id) throws Exception {
		matchRepository.deleteById(id);
		
	}

}
