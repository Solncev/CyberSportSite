package ru.kpfu.itis.csport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.model.Team;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.repository.TeamRepository;
import ru.kpfu.itis.csport.service.TeamService;

/**
 * @author krylov
 */
@Service
public class TeamServiceImpl implements TeamService {

  private TeamRepository teamRepository;

  @Autowired
  public TeamServiceImpl(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  public List<Team> findAll() {
    return teamRepository.findAll();
  }

  @Override
  public List<Team> findByOwnerAndSize(User owner, int size) {
    //todo
    return null;
  }
}
