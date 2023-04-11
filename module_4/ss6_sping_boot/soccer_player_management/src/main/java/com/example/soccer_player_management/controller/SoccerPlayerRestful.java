package com.example.soccer_player_management.controller;

import com.example.soccer_player_management.dto.SoccerPlayerCreateAndUpdateDTO;
import com.example.soccer_player_management.model.SoccerPlayer;
import com.example.soccer_player_management.service.ISoccerPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/soccer-player")
@CrossOrigin("*")
public class SoccerPlayerRestful {
    @Autowired
    private ISoccerPlayerService soccerPlayerService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<SoccerPlayer> getList(@PageableDefault(size = 3) Pageable pageable
            , @RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                      @RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, Model model) {
        return soccerPlayerService.findAll(name, startDate, endDate, pageable);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSoccer(@RequestBody SoccerPlayerCreateAndUpdateDTO soccerPlayerDTO) {
        soccerPlayerService.create(soccerPlayerDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("detail/{id}")
    public SoccerPlayer getSoccerPlayerDetail(@PathVariable int id) {
        return soccerPlayerService.findById(id).get();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteSoccer(@PathVariable int id) {
        soccerPlayerService.deleteSoccerPlayer(soccerPlayerService.findById(id).get());
    }
}
