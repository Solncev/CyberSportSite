package ru.kpfu.itis.csport.model;

import java.util.Collection;
import javax.persistence.*;

/**
 * @author krylov
 */
@Entity
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int memberCount;

  @ManyToOne(optional = false)
  private User leader;

  @OneToMany(mappedBy = "team_id")
  private Collection<TeamMember> members;

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

  public int getMemberCount() {
    return memberCount;
  }

  public void setMemberCount(int memberCount) {
    this.memberCount = memberCount;
  }

  public User getLeader() {
    return leader;
  }

  public void setLeader(User leader) {
    this.leader = leader;
  }

  public Collection<TeamMember> getMembers() {
    return members;
  }

  public void setMembers(Collection<TeamMember> members) {
    this.members = members;
  }
}
