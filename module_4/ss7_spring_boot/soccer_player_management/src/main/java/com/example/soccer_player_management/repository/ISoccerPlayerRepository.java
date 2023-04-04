package com.example.soccer_player_management.repository;

import com.example.soccer_player_management.model.SoccerPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoccerPlayerRepository extends JpaRepository<SoccerPlayer, Integer> {
    Page<SoccerPlayer> findAllByNameContaining(Pageable pageable, String name);
}
