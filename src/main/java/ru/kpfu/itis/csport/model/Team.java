package ru.kpfu.itis.csport.model;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, updatable = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "leader_id")
    private User leader;

    @Column(name = "first_player")
    private String firstPlayer;

    @Column(name = "second_player")
    private String secondPlayer;

    @Column(name = "third_player")
    private String thirdPlayer;

    @Column(name = "fourth_player")
    private String fourthPlayer;

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

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public String getThirdPlayer() {
        return thirdPlayer;
    }

    public void setThirdPlayer(String thirdPlayer) {
        this.thirdPlayer = thirdPlayer;
    }

    public String getFourthPlayer() {
        return fourthPlayer;
    }

    public void setFourthPlayer(String fourthPlayer) {
        this.fourthPlayer = fourthPlayer;
    }
}
