package com.example.soccer_player_management.controller;

import com.example.soccer_player_management.model.SoccerPlayer;
import com.example.soccer_player_management.service.ISoccerPlayerService;
import com.example.soccer_player_management.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("soccer-player")
public class SoccerPlayerController {
    @Autowired
    private ISoccerPlayerService soccerPlayerService;
    @Autowired
    private ITeamService teamService;

    @GetMapping("")
    public String showSoccerPlayerList(@PageableDefault(size = 3) Pageable pageable
                , @RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, Model model) {
        Sort sort = null;
        if (name == name) {
            sort = Sort.by("dateOfBirth").ascending();
        } else {
            sort = Sort.by("name").ascending();
        }
        model.addAttribute("name", name);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<SoccerPlayer> soccerPlayerPage = soccerPlayerService.findAll(name , startDate , endDate , sortedPageable);
        model.addAttribute("soccerPlayerList", soccerPlayerPage);
        List<Integer> pageNumberList = new ArrayList<>();
        for (int i = 1; i <= soccerPlayerPage.getTotalPages(); i++) {
            pageNumberList.add(i);
        }
        model.addAttribute("pageNumberList", pageNumberList);
        return "/list";
    }

    @GetMapping("/detail")
    public String detailSoccerPlayer(@RequestParam int id, Model model) {
        SoccerPlayer soccerPlayer = soccerPlayerService.findById(id).get();
        model.addAttribute("soccerPlayer", soccerPlayer);
        return "/detail";
    }

    @GetMapping("/delete")
    public String deleteSoccerPlayer(@RequestParam int deleteId) {
        SoccerPlayer soccerPlayer = soccerPlayerService.findById(deleteId).get();
        soccerPlayerService.deleteSoccerPlayer(soccerPlayer);
        return "redirect:/soccer-player";
    }

    @GetMapping("/create")
    public String showCreateSoccerPlayer(Model model) {
        model.addAttribute("soccerPlayer", new SoccerPlayer());
        model.addAttribute("teams", teamService.findAll());
        return "/create";
    }

    @PostMapping("/create")
    public String createSoccerPlayer(@ModelAttribute SoccerPlayer soccerPlayer) {
        soccerPlayerService.create(soccerPlayer);
        return "redirect:/soccer-player";
    }

    @GetMapping("/update")
    public String showUpdateSoccerPlayer(@RequestParam int id, Model model) {
        model.addAttribute("soccerPlayer", soccerPlayerService.findById(id));
        model.addAttribute("teams", teamService.findAll());
        return "/update";
    }

    @PostMapping("/update")
    public String updateSoccerPlayer(@ModelAttribute SoccerPlayer soccerPlayer) {
        soccerPlayerService.update(soccerPlayer);
        return "redirect:/soccer-player";
    }
}
