package com.example.soccer_player_management.dto;

import com.example.soccer_player_management.model.Team;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import javax.validation.constraints.*;

public class SoccerPlayerCreateDTO implements Validator {
    private int id;
    private String code;
    @NotBlank(message = "Tên không được để trống!")
    @Pattern(regexp = "^[A-Za-z ]{5,100}$", message = "Tên phải từ 5-100 kí tự và không chứa kí tự đặc biệt!")
    private String name;
    private String dateOfBirth;
    @Min(value = 16, message = "Tuổi phải từ 16 đến 100!")
    @Max(value = 100, message = "Tuổi phải từ 16 đến 100!")
    private int age;
    @Pattern(regexp = "^[0-9]*$", message = "Kinh nghiệm phải là số nguyên dương!")
    private int exp;
    @Pattern(regexp = "^(Trung vệ|Hậu vệ|Tiền vệ|Tiền đạo|Thủ môn)$", message = "Vị trí phải là 1 trong 4 vị trí!")
    private String location;
    private String image;
    private Team team;

    public SoccerPlayerCreateDTO() {
    }

    public SoccerPlayerCreateDTO(int id, String code, String name, String dateOfBirth, int age, int exp, String location, String image, Team team) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
