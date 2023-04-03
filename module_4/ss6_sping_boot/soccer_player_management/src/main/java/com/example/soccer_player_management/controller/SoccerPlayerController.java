package com.example.soccer_player_management.controller;

import com.example.soccer_player_management.model.SoccerPlayer;
import com.example.soccer_player_management.service.ISoccerPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("soccer-player")
public class SoccerPlayerController {
    @Autowired
    private ISoccerPlayerService soccerPlayerService;

    @GetMapping("")
    public String showSoccerPlayerList(@PageableDefault(size = 3) Pageable pageable
            , @RequestParam(required = false) String name, Integer page, Model model) {
        if (name == null) {
            name = "";
        }
        if (page == null) {
            page = 1;
        }
        Sort sort = null;
        if (name == name) {
            sort = Sort.by("dateOfBirth").ascending();
        } else {
            sort = Sort.by("name").ascending();
        }
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<SoccerPlayer> soccerPlayerPage = soccerPlayerService.findAll(sortedPageable, name);
        model.addAttribute("soccerPlayerList", soccerPlayerPage);

        model.addAttribute("name", name);
        List<Integer> pageNumberList = new ArrayList<>();
        for (int i = 1; i <= soccerPlayerPage.getTotalPages(); i++) {
            pageNumberList.add(i);
        }
        for (int i = 1; i <= pageNumberList.size(); i++) {
            if (i == page) {
                model.addAttribute("page", i - 1);
            }
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
    public String deleteSoccerPlayer(@RequestBody SoccerPlayer soccerPlayer) {
        soccerPlayerService.deleteSoccerPlayer(soccerPlayer);
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

    @GetMapping("/update")
    public String showUpdateSoccerPlayer(@RequestParam int id, Model model) {
        model.addAttribute("soccerPlayer", soccerPlayerService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String updateSoccerPlayer(@ModelAttribute SoccerPlayer soccerPlayer) {
        soccerPlayerService.update(soccerPlayer);
        return "redirect:/soccer-player";
    }
}
