package com.example.football_management.repository.impl;

import com.example.football_management.BaseRepository;
import com.example.football_management.model.SoccerPlayer;
import com.example.football_management.repository.ISoccerPlayerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class SoccerPlayerRepository implements ISoccerPlayerRepository {

    @Override
    public List<SoccerPlayer> findAll() {
        List<SoccerPlayer> soccerPlayerList = BaseRepository.entityManager
                .createQuery("select s from SoccerPlayer as s" , SoccerPlayer.class).getResultList();
        return soccerPlayerList;
    }

    @Override
    public SoccerPlayer findById(int id) {
        SoccerPlayer soccerPlayer = BaseRepository.entityManager
                .find(SoccerPlayer.class , id);
        return soccerPlayer;
    }

    @Override
    public void deleteSoccerPlayer(int id) {
        EntityTransaction entityTransaction = BaseRepository.entityManager.getTransaction();
        entityTransaction.begin();
        BaseRepository.entityManager.remove(findById(id));
        entityTransaction.commit();
    }

    @Override
    public void create(SoccerPlayer soccerPlayer) {
        EntityTransaction entityTransaction = BaseRepository.entityManager.getTransaction();
        entityTransaction.begin();
        BaseRepository.entityManager.persist(soccerPlayer);
        entityTransaction.commit();
    }

    @Override
    public void update(SoccerPlayer soccerPlayer) {
        EntityTransaction entityTransaction = BaseRepository.entityManager.getTransaction();
        entityTransaction.begin();
        BaseRepository.entityManager.merge(soccerPlayer);
        entityTransaction.commit();
    }
}
