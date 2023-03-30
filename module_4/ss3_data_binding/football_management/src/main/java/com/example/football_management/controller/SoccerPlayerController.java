package com.example.football_management.controller;

import com.example.football_management.model.SoccerPlayer;
import com.example.football_management.service.ISoccerPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("soccerPlayer", soccerPlayer);
        return "/detail";
    }

    @GetMapping("/delete")
    public String deleteSoccerPlayer(@RequestParam int idDelete) {
        soccerPlayerService.deleteSoccerPlayer(idDelete);
        return "redirect:/soccer-player";
    }

    @GetMapping("/create")
    public String showCreateSoccerPlayer(Model model) {
        model.addAttribute("soccerPlayer", new SoccerPlayer());
        return "/create";
    }

    @PostMapping("/create")
    public String createSoccerPlayer(@ModelAttribute SoccerPlayer soccerPlayer) {
        soccerPlayerService.create(soccerPlayer);
        return "redirect:/soccer-player";
    }

    @GetMapping("/update/{id}")
    public String showUpdateSoccerPlayer(@PathVariable("id") int id, Model model) {
        model.addAttribute("soccerPlayer", soccerPlayerService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String updateSoccerPlayer(@ModelAttribute SoccerPlayer soccerPlayer) {
        soccerPlayerService.update(soccerPlayer);
        return "redirect:/soccer-player";
    }
}
