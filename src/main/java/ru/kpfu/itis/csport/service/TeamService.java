package ru.kpfu.itis.csport.service;

import ru.kpfu.itis.csport.model.Team;
import ru.kpfu.itis.csport.model.User;

import java.util.List;

public interface TeamService {
    void create(Team team) throws Exception;

    void update(Team team);

    void delete(Team team);

    Team getOne(int id);

    List<Team> findAll();

    List<Team> findByOwnerAndSize(User owner, int size);
}
