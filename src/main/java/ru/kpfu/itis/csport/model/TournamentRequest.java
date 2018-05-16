package ru.kpfu.itis.csport.model;

import javax.persistence.*;

/**
 * @author krylov
 */
@Entity
@Table(name = "tournament_request")
public class TournamentRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id; //todo composite pk

  @ManyToOne
  @JoinColumn(name = "tournament_id")
  private Tournament tournament;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "team_id")
  private Team team;

  @Column(nullable = false)
  private boolean accepted = false;

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

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public boolean isAccepted() {
    return accepted;
  }

  public void setAccepted(boolean accepted) {
    this.accepted = accepted;
  }
}
