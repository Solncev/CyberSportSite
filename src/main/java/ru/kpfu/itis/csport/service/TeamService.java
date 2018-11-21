package ru.kpfu.itis.csport.service;

import ru.kpfu.itis.csport.model.Team;

public interface TeamService {
    Team create(Team team);

    void update(Team team);

    void delete(Team team);

    Team getOne(int id);

    Team getByName(String name);
}
