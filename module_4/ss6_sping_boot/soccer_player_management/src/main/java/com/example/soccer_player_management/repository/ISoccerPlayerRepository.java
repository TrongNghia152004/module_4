package com.example.soccer_player_management.repository;

import com.example.soccer_player_management.model.SoccerPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface ISoccerPlayerRepository extends JpaRepository<SoccerPlayer, Integer> {
    Page<SoccerPlayer> findAllByNameContaining(Pageable pageable, String name);
    @Transactional
    @Query(value = "select * from soccer_player where ? <= date_of_birth <= ?", nativeQuery = true )
    Page<SoccerPlayer> findAllByDateOfBirthContaining(Pageable pageable , Date to , Date from);
}
