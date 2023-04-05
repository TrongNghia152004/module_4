package com.example.soccer_player_management.service.impl;

import com.example.soccer_player_management.model.SoccerPlayer;
import com.example.soccer_player_management.repository.ISoccerPlayerRepository;
import com.example.soccer_player_management.service.ISoccerPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SoccerPlayerService implements ISoccerPlayerService {
    @Autowired
    private ISoccerPlayerRepository soccerPlayerRepository;

    @Override
    public Page<SoccerPlayer> findAll(String name , LocalDate startDate, LocalDate endDate , Pageable pageable) {
        return soccerPlayerRepository.findAllByNameAndBirthdateBetween(name , startDate , endDate , pageable);
    }

    @Override
    public Optional<SoccerPlayer> findById(int id) {
        return soccerPlayerRepository.findById(id);
    }

    @Override
    public void deleteSoccerPlayer(SoccerPlayer soccerPlayer) {
        soccerPlayerRepository.delete(soccerPlayer);
    }

    @Override
    public void create(SoccerPlayer soccerPlayer) {
        soccerPlayerRepository.save(soccerPlayer);
    }

    @Override
    public void update(SoccerPlayer soccerPlayer) {
        soccerPlayerRepository.save(soccerPlayer);
    }
}
