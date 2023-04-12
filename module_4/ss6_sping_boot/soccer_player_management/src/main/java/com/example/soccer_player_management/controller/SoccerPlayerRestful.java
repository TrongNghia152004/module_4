package com.example.soccer_player_management.controller;

import com.example.soccer_player_management.dto.SoccerPlayerCreateAndUpdateDTO;
import com.example.soccer_player_management.model.SoccerPlayer;
import com.example.soccer_player_management.model.Team;
import com.example.soccer_player_management.service.ISoccerPlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/soccer-player")
@CrossOrigin("*")
public class SoccerPlayerRestful {
    @Autowired
    private ISoccerPlayerService soccerPlayerService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<SoccerPlayer> getList(@PageableDefault(size = 3) Pageable pageable
            , @RequestParam(defaultValue = "") String name) {
        return soccerPlayerService.findAllByName(name, pageable);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createSoccerPlayer(@Validated @RequestBody SoccerPlayerCreateAndUpdateDTO soccerPlayerCreateAndUpdateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new LinkedHashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                if (!map.containsKey(error.getField())) {
                    map.put(error.getField(), error.getDefaultMessage());
                }
            }
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        } else {
        SoccerPlayer soccerPlayer = new SoccerPlayer();
        BeanUtils.copyProperties(soccerPlayerCreateAndUpdateDTO, soccerPlayer);
        soccerPlayerService.create(soccerPlayerCreateAndUpdateDTO);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("detail/{id}")
    public SoccerPlayer getSoccerPlayerDetail(@PathVariable int id) {
        return soccerPlayerService.findById(id).get();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("update/{id}")
    public void updateSoccerPlayer(@RequestBody SoccerPlayerCreateAndUpdateDTO soccerPlayerCreateAndUpdateDTO, @PathVariable int id) {
        soccerPlayerService.update(soccerPlayerCreateAndUpdateDTO, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteSoccer(@PathVariable int id) {
        soccerPlayerService.deleteSoccerPlayer(soccerPlayerService.findById(id).get());
    }
}
