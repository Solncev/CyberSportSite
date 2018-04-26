package ru.kpfu.itis.csport.model;

import javax.persistence.*;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:15 PM
 */
@Entity
@Table(name = "computer_game")
public class ComputerGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Lob
    private String description;

    @Column(nullable = false)
    private int teamSize;

    @Column(nullable = false)
    private String photoLink;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}
