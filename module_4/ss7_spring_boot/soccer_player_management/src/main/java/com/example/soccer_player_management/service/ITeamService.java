package com.example.soccer_player_management.service;

import com.example.soccer_player_management.model.Team;

import java.util.List;

public interface ITeamService {
    List<Team> findAll();
}
