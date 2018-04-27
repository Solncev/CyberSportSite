package ru.kpfu.itis.csport.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author krylov
 */
@Entity
public class TeamMember {

  @ManyToOne
  private Team team;

  @Column(nullable = false)
  private String username;

  @Column
  private String fullName;

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TeamMember)) return false;
    TeamMember that = (TeamMember) o;
    return Objects.equals(getTeam(), that.getTeam()) &&
        Objects.equals(getUsername(), that.getUsername());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTeam(), getUsername());
  }
}
