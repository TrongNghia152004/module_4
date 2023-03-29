package com.example.football_management.controller;

import com.example.football_management.model.SoccerPlayer;
import com.example.football_management.service.ISoccerPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/soccer-player")
public class SoccerPlayerController {
    @Autowired
    private ISoccerPlayerService soccerPlayerService;

    @GetMapping("")
    public String showSoccerPlayerList(Model model) {
        model.addAttribute("soccerPlayerList", soccerPlayerService.findAll());
        return "/list";
    }

    @GetMapping("/detail/{id}")
    public String detailSoccerPlayer(@PathVariable("id") int id, Model model) {
        SoccerPlayer soccerPlayer = soccerPlayerService.findById(id);
        model.addAttribute("soccerPlayer" , soccerPlayer);
        return "/detail";
    }
}
