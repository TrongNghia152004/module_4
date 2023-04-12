package com.example.soccer_player_management.controller;

import com.example.soccer_player_management.dto.SoccerPlayerCreateAndUpdateDTO;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("soccerPlayerFavourites")
@RequestMapping("soccer-player")
public class SoccerPlayerController {

    @ModelAttribute("soccerPlayerFavourites")
    public List<SoccerPlayer> soccerPlayerList() {
        return new ArrayList<>();
    }

    @Autowired
    private ISoccerPlayerService soccerPlayerService;
    @Autowired
    private ITeamService teamService;

    @GetMapping("/football-team")
    public String footballTeam(Model model) {
        List<SoccerPlayer> soccerPlayerList = soccerPlayerService.footballTeam();
        model.addAttribute("soccerPlayerList", soccerPlayerList);
        return "/football-team";
    }

    @GetMapping("")
    public String showSoccerPlayerList(@PageableDefault(size = 3) Pageable pageable
            , @RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, Model model) {
        Sort sort = null;
        sort = Sort.by("name").ascending();
        if (name == name) {
            sort = Sort.by("dateOfBirth").ascending();
        }
        model.addAttribute("name", name);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<SoccerPlayer> soccerPlayerPage = soccerPlayerService.findAll(name, startDate, endDate, sortedPageable);
        model.addAttribute("soccerPlayerList", soccerPlayerPage);
        List<Integer> pageNumberList = new ArrayList<>();
        for (int i = 1; i <= soccerPlayerPage.getTotalPages(); i++) {
            pageNumberList.add(i);
        }
        model.addAttribute("pageNumberList", pageNumberList);
        return "/list";
    }

    @GetMapping("/detail")
    public String detailSoccerPlayer(@RequestParam int id, Model model, HttpServletResponse response) {
        SoccerPlayer player = soccerPlayerService.findById(id).get();
        Cookie cookie = new Cookie("favourites", player + "");
        cookie.setMaxAge(1 * 24 * 60 * 60);
        response.addCookie(cookie);
        model.addAttribute("player", player);
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
        model.addAttribute("soccerPlayerCreateAndUpdateDTO", new SoccerPlayerCreateAndUpdateDTO());
        model.addAttribute("teams", teamService.findAll());
        return "/create";
    }

    @PostMapping("/create")
    public String createSoccerPlayer(@Valid @ModelAttribute SoccerPlayerCreateAndUpdateDTO soccerPlayerCreateAndUpdateDTO, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, Model model) {
        new SoccerPlayerCreateAndUpdateDTO().validate(soccerPlayerCreateAndUpdateDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamService.findAll());
            return "/create";
        }
        redirectAttributes.addFlashAttribute("msg", "Thêm mới thành công");
        soccerPlayerService.create(soccerPlayerCreateAndUpdateDTO);
        return "redirect:/soccer-player";
    }

    @GetMapping("/update")
    public String showUpdateSoccerPlayer(@RequestParam int id, Model model) {
        model.addAttribute("soccerPlayerCreateAndUpdateDTO", soccerPlayerService.findById(id));
        model.addAttribute("teams", teamService.findAll());
        return "/update";
    }

    @PostMapping("/update")
    public String updateSoccerPlayer(@Valid @ModelAttribute SoccerPlayerCreateAndUpdateDTO soccerPlayerCreateAndUpdateDTO, BindingResult bindingResult, Model model
            , RedirectAttributes redirectAttributes) {
        new SoccerPlayerCreateAndUpdateDTO().validate(soccerPlayerCreateAndUpdateDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamService.findAll());
            return "/update";
        }
        redirectAttributes.addFlashAttribute("msg", "Chỉnh sửa thành công");
        soccerPlayerService.update(soccerPlayerCreateAndUpdateDTO , soccerPlayerCreateAndUpdateDTO.getId());
        return "redirect:/soccer-player";
    }

    @GetMapping("/register")
    public String registerSoccerPlayer(@RequestParam int registerId) {
        SoccerPlayer soccerPlayer = soccerPlayerService.findById(registerId).get();
        soccerPlayerService.register(soccerPlayer);
        return "redirect:/soccer-player";
    }

    @GetMapping("/unregister")
    public String unregisterSoccerPlayer(@RequestParam int unregisterId) {
        SoccerPlayer soccerPlayer = soccerPlayerService.findById(unregisterId).get();
        soccerPlayerService.unRegister(soccerPlayer);
        return "redirect:/soccer-player";
    }

    @GetMapping("/favourites")
    public String favourites(@RequestParam() int id, @ModelAttribute("soccerPlayerFavourites") List<SoccerPlayer> soccerPlayerList
            , RedirectAttributes redirectAttributes, Model model) {
        SoccerPlayer player = soccerPlayerService.findById(id).get();
        model.addAttribute("player", player);
        soccerPlayerList.add(player);
        redirectAttributes.addFlashAttribute("msg", "Thêm vào danh sách yêu thích thành công");
        return "redirect:/soccer-player";
    }

    @GetMapping("/favourite-list")
    public String listSoccerPlayerFavourites(@ModelAttribute("soccerPlayerFavourites") List<SoccerPlayer> soccerPlayerList
            , Model model) {
        model.addAttribute("favouritesList", soccerPlayerList);
        return "/favourites";
    }
    @ExceptionHandler(Exception.class)
    public String handle(){
        return "/error";
    }
}
