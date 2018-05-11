package ru.kpfu.itis.csport.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

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

    @OneToMany(mappedBy = "tournament")
    private Collection<TournamentMatch> matches;

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

    public Collection<TournamentMatch> getMatches() {
        return matches;
    }

    public void setMatches(Collection<TournamentMatch> matches) {
        this.matches = matches;
    }

}
