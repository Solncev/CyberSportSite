package ru.kpfu.itis.csport.model;

import javax.persistence.*;
import java.util.Date;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:34 PM
 */
@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private ComputerGame game;

    @Column(nullable = false)
    private int teamCount;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;    //todo

    @Column(nullable = false)
    private String status = "upcoming"; //todo

    @Column(nullable = false)
    private Date date = new Date();

    @Lob
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ComputerGame getGame() {
        return game;
    }

    public void setGame(ComputerGame game) {
        this.game = game;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
