package com.example.soccer_player_management.service.impl;

import com.example.soccer_player_management.dto.SoccerPlayerCreateAndUpdateDTO;
import com.example.soccer_player_management.model.SoccerPlayer;
import com.example.soccer_player_management.model.Team;
import com.example.soccer_player_management.repository.ISoccerPlayerRepository;
import com.example.soccer_player_management.service.ISoccerPlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SoccerPlayerService implements ISoccerPlayerService {
    @Autowired
    private ISoccerPlayerRepository soccerPlayerRepository;

    @Override
    public Page<SoccerPlayer> findAll(String name, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return soccerPlayerRepository.findAllByNameAndBirthdateBetween(name, startDate, endDate, pageable);
    }

    @Override
    public Page<SoccerPlayer> findAllByName(String name, Pageable pageable) {
        return soccerPlayerRepository.findAllByNameContaining(name, pageable);
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
    public void create(SoccerPlayerCreateAndUpdateDTO soccerPlayerCreateAndUpdateDTO) {
        SoccerPlayer soccerPlayer = new SoccerPlayer();
        BeanUtils.copyProperties(soccerPlayerCreateAndUpdateDTO, soccerPlayer);
        soccerPlayerRepository.save(soccerPlayer);
    }

    @Override
    public void update(SoccerPlayerCreateAndUpdateDTO soccerPlayerCreateAndUpdateDTO , int id) {
        SoccerPlayer soccerPlayer = soccerPlayerRepository.findById(id).get();
        BeanUtils.copyProperties(soccerPlayerCreateAndUpdateDTO, soccerPlayer);
        soccerPlayerRepository.save(soccerPlayer);
    }
    @Override
    public void register(SoccerPlayer soccerPlayer) {
        List<SoccerPlayer> soccerPlayerList = soccerPlayerRepository.findAll();
        List<SoccerPlayer> registeredList = soccerPlayerRepository.findAllByStatus();
        if (registeredList.size() < 11) {
            for (int i = 0; i < soccerPlayerList.size(); i++) {
                if (soccerPlayerList.get(i).getId() == soccerPlayer.getId()) {
                    soccerPlayerList.get(i).setStatus("Đã đăng kí");
                    soccerPlayerRepository.save(soccerPlayerList.get(i));
                }
            }
        }
    }

    @Override
    public void unRegister(SoccerPlayer soccerPlayer) {
        List<SoccerPlayer> soccerPlayerList = soccerPlayerRepository.findAll();
            for (int i = 0; i < soccerPlayerList.size(); i++) {
                if (soccerPlayerList.get(i).getId() == soccerPlayer.getId()) {
                    soccerPlayerList.get(i).setStatus("Dự bị");
                    soccerPlayerRepository.save(soccerPlayerList.get(i));
            }
        }
    }

    @Override
    public List<SoccerPlayer> footballTeam() {
        return soccerPlayerRepository.findAllByStatus();
    }
}
