package com.example.soccer_player_management.service;

import com.example.soccer_player_management.model.SoccerPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

public interface ISoccerPlayerService {
    Page<SoccerPlayer> findAll(Pageable pageable, String name);
    Optional<SoccerPlayer> findById(int id);

    void deleteSoccerPlayer(SoccerPlayer soccerPlayer);

    void create(SoccerPlayer soccerPlayer);

    void update(SoccerPlayer soccerPlayer);
}
