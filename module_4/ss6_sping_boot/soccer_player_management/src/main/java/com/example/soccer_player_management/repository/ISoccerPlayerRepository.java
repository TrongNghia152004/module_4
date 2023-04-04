package com.example.soccer_player_management.repository;

import com.example.soccer_player_management.model.SoccerPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface ISoccerPlayerRepository extends JpaRepository<SoccerPlayer, Integer> {
    @Transactional
    @Query("SELECT e FROM SoccerPlayer e WHERE e.name LIKE %:name% AND (e.dateOfBirth >= :startDate OR :startDate is null) AND (e.dateOfBirth <= :endDate OR :endDate is null)")
    Page<SoccerPlayer> findAllByNameAndBirthdateBetween(@Param("name") String name, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);
}
