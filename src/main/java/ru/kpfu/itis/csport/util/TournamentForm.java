package ru.kpfu.itis.csport.util;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/28/18 5:26 PM
 */
public class TournamentForm {

    @NotNull
    private int game;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date startDate;

    private String photo;

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
