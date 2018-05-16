package ru.kpfu.itis.csport.util;

import javax.validation.constraints.NotNull;

/**
 * @author krylov
 */
public class TournamentApplicationForm {

  @NotNull
  private int teamId;

  @NotNull
  private int tournamentId;

  private String teamName;
  private String player1;
  private String player2;
  private String player3;
  private String player4;

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }

  public int getTournamentId() {
    return tournamentId;
  }

  public void setTournamentId(int tournamentId) {
    this.tournamentId = tournamentId;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public String getPlayer1() {
    return player1;
  }

  public void setPlayer1(String player1) {
    this.player1 = player1;
  }

  public String getPlayer2() {
    return player2;
  }

  public void setPlayer2(String player2) {
    this.player2 = player2;
  }

  public String getPlayer3() {
    return player3;
  }

  public void setPlayer3(String player3) {
    this.player3 = player3;
  }

  public String getPlayer4() {
    return player4;
  }

  public void setPlayer4(String player4) {
    this.player4 = player4;
  }

}
