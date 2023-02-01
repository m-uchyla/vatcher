package com.fmdgroup.vatcher.services;

import java.util.List;

import com.fmdgroup.vatcher.model.Match;

public interface IMatchService {
void createNewMatch(Match match) throws Exception;
List<Match> findAllMatches();
void deleteMatch(Long id) throws Exception;

}
