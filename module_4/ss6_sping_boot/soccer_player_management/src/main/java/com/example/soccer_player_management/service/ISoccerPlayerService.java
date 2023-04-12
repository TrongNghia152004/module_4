package com.example.soccer_player_management.service;

import com.example.soccer_player_management.dto.SoccerPlayerCreateAndUpdateDTO;
import com.example.soccer_player_management.model.SoccerPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ISoccerPlayerService {
    Page<SoccerPlayer> findAll(String name, LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<SoccerPlayer> findAllByName(String name, Pageable pageable);

    Optional<SoccerPlayer> findById(int id);

    void deleteSoccerPlayer(SoccerPlayer soccerPlayer);

    void create(SoccerPlayerCreateAndUpdateDTO soccerPlayerCreateAndUpdateDTO);

    void update(SoccerPlayerCreateAndUpdateDTO soccerPlayerCreateAndUpdateDTO , int id);

    void register(SoccerPlayer soccerPlayer);

    List<SoccerPlayer> footballTeam();

    void unRegister(SoccerPlayer soccerPlayer);
}
