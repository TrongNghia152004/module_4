package com.example.soccer_player_management.controller;

import com.example.soccer_player_management.model.Team;
import com.example.soccer_player_management.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private ITeamService teamService;
    @GetMapping("")
    public List<Team> showList(){
        return teamService.findAll();
    }
}
