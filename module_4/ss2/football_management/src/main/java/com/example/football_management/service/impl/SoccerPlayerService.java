package com.example.football_management.service.impl;

import com.example.football_management.model.SoccerPlayer;
import com.example.football_management.repository.ISoccerPlayerRepository;
import com.example.football_management.service.ISoccerPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SoccerPlayerService implements ISoccerPlayerService {
    @Autowired
    private ISoccerPlayerRepository soccerPlayerRepository;
    @Override
    public List<SoccerPlayer> findAll() {
        return soccerPlayerRepository.findAll();
    }

    @Override
    public SoccerPlayer findById(int id) {
        return soccerPlayerRepository.findById(id);
    }

    @Override
    public void deleteSoccerPlayer(int id) {
        soccerPlayerRepository.deleteSoccerPlayer(id);
    }
}
