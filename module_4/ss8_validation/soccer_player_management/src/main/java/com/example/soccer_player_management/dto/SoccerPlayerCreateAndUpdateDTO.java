package com.example.soccer_player_management.dto;

import com.example.soccer_player_management.model.Team;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

public class SoccerPlayerCreateAndUpdateDTO implements Validator {
    private int id;
    private String code;
    @NotBlank(message = "Tên không được để trống!")
    @Pattern(regexp = "^[A-Za-z ]{5,100}$", message = "Tên phải từ 5-100 kí tự và không chứa kí tự đặc biệt!")
    private String name;
    private String dateOfBirth;
    @Pattern(regexp = "^[0-9]*[1-9][0-9]*$", message = "Kinh nghiệm phải là số nguyên dương!")
    private String exp;
    @Pattern(regexp = "^(Trung vệ|Hậu vệ|Tiền vệ|Tiền đạo|Thủ môn)$", message = "Vị trí phải là 1 trong 4 vị trí(Trung vệ,  Hậu vệ,Tiền vệ,Tiền đạo,Thủ môn)!")
    private String location;
    private String image;
    private Team team;

    public SoccerPlayerCreateAndUpdateDTO() {
    }

    public SoccerPlayerCreateAndUpdateDTO(int id, String code, String name, String dateOfBirth, String exp, String location, String image, Team team) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
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
        SoccerPlayerCreateAndUpdateDTO soccerPlayerCreateAndUpdateDTO = (SoccerPlayerCreateAndUpdateDTO) target;
        if (!soccerPlayerCreateAndUpdateDTO.dateOfBirth.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
            Period age = Period.between(LocalDate.parse(soccerPlayerCreateAndUpdateDTO.getDateOfBirth()), LocalDate.now());
            int years = age.getYears();
            if (16 > years || years > 100) {
                errors.rejectValue("dateOfBirth", "", "Tuổi phải từ 16 đến 100");
            }
        }
    }
}
