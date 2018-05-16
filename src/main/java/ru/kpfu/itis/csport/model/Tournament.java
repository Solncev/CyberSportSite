package ru.kpfu.itis.csport.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:34 PM
 */
@Entity
public class Tournament {

    public enum Status {UPCOMING, ACTIVE, PAST}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private Discipline discipline;

    @Column(name = "team_count", nullable = false)
    private int teamCount;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.UPCOMING;

    @Column(nullable = false)
    private Date date = new Date();

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "photo_link")
    private String photoLink;

    @OneToMany(mappedBy = "tournament", cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TournamentMatch> matches = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "tournament")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TournamentRequest> requests = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public List<TournamentMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<TournamentMatch> matches) {
        this.matches = matches;
    }

    public Collection<TournamentRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<TournamentRequest> requests) {
        this.requests = requests;
    }

}
