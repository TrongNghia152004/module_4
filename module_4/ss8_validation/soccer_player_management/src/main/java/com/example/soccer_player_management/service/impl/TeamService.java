package com.example.soccer_player_management.service.impl;

import com.example.soccer_player_management.model.Team;
import com.example.soccer_player_management.repository.ITeamRepository;
import com.example.soccer_player_management.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamService implements ITeamService {
    @Autowired
    private ITeamRepository teamRepository;
    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }
}
