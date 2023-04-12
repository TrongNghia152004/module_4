package com.example.soccer_player_management.controller;

import com.example.soccer_player_management.model.Team;
import com.example.soccer_player_management.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
@CrossOrigin("*")
public class TeamController {
    @Autowired
    private ITeamService teamService;
    @GetMapping("")
    public List<Team> showList(){
        return teamService.findAll();
    }
}
