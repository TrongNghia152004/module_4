package com.example.soccer_player_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "team" , cascade = CascadeType.ALL)
    @JsonManagedReference
     private Set<SoccerPlayer> soccerPlayerSet;

    public Team() {
    }

    public Team(int id, String name, Set<SoccerPlayer> soccerPlayerSet) {
        this.id = id;
        this.name = name;
        this.soccerPlayerSet = soccerPlayerSet;
    }

    public Set<SoccerPlayer> getSoccerPlayerSet() {
        return soccerPlayerSet;
    }

    public void setSoccerPlayerSet(Set<SoccerPlayer> soccerPlayerSet) {
        this.soccerPlayerSet = soccerPlayerSet;
    }

    public Team(int id) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
