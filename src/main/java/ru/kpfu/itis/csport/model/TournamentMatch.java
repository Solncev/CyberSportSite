package ru.kpfu.itis.csport.model;

import javax.persistence.*;

/**
 * @author krylov
 */
@Entity
@Table(name = "tournament_match")
public class TournamentMatch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(optional = false)
  private Tournament tournament;

  @ManyToOne
  private Team team1;

  @ManyToOne
  private Team team2;

  @Column(columnDefinition = "text")
  private String description;

  @Column
  private int round;

  @Column
  private Integer winner;

  @Column(name = "team1_winner")
  private Integer team1Winner;

  @Column(name = "team2_winner")
  private Integer team2Winner;

  @ManyToOne
  @JoinColumn(name = "next_match")
  private TournamentMatch nextMatch;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Tournament getTournament() {
    return tournament;
  }

  public void setTournament(Tournament tournament) {
    this.tournament = tournament;
  }

  public Team getTeam1() {
    return team1;
  }

  public void setTeam1(Team team1) {
    this.team1 = team1;
  }

  public Team getTeam2() {
    return team2;
  }

  public void setTeam2(Team team2) {
    this.team2 = team2;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getWinner() {
    return winner;
  }

  public void setWinner(Integer winner) {
    this.winner = winner;
  }

  public Integer getTeam1Winner() {
    return team1Winner;
  }

  public void setTeam1Winner(Integer team1Winner) {
    this.team1Winner = team1Winner;
  }

  public Integer getTeam2Winner() {
    return team2Winner;
  }

  public void setTeam2Winner(Integer team2Winner) {
    this.team2Winner = team2Winner;
  }

  public TournamentMatch getNextMatch() {
    return nextMatch;
  }

  public void setNextMatch(TournamentMatch nextMatch) {
    this.nextMatch = nextMatch;
  }

  public int getRound() {
    return round;
  }

  public void setRound(int round) {
    this.round = round;
  }

  public Team getWinnerTeam() {
    if (winner == 1)
      return team1;
    else if (winner == 2)
      return team2;
    return null;
  }
}
