package ru.kpfu.itis.csport.service;

import ru.kpfu.itis.csport.model.Team;

public interface TeamService {
    void create(Team team) throws Exception;

    void update(Team team);

    void delete(Team team);

    Team getOne(int id);
}
