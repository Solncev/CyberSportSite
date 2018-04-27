package ru.kpfu.itis.csport.service;

import java.util.List;

import ru.kpfu.itis.csport.model.Team;
import ru.kpfu.itis.csport.model.User;

/**
 * @author krylov
 */
public interface TeamService {

  List<Team> findAll();

  List<Team> findByOwnerAndSize(User owner, int size);
}
