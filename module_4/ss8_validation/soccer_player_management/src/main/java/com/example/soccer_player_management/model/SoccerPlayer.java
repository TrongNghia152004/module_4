package com.example.soccer_player_management.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "soccer_player")
public class SoccerPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "age")
    private int age;
    @Column(name = "exp")
    private String exp;
    @Column(name = "location")
    private String location;
    @Column(name = "image")
    private String image;
    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public SoccerPlayer() {
    }

    public SoccerPlayer(int id, String code, String name, String dateOfBirth, int age, String exp, String location, String image, Team team) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.exp = exp;
        this.location = location;
        this.image = image;
        this.team = team;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
