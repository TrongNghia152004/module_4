package com.example.football_management.service;

import com.example.football_management.model.SoccerPlayer;

import java.util.List;

public interface ISoccerPlayerService {
    List<SoccerPlayer> findAll();
    SoccerPlayer findById(int id);
}