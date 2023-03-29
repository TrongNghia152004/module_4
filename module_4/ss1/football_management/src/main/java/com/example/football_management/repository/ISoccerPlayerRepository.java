package com.example.football_management.repository;

import com.example.football_management.model.SoccerPlayer;

import java.util.List;

public interface ISoccerPlayerRepository {
    List<SoccerPlayer> findAll();
    SoccerPlayer findById(int id);
}
