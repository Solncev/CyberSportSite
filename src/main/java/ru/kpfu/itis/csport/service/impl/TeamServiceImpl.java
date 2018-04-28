package ru.kpfu.itis.csport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.model.Team;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.repository.TeamRepository;
import ru.kpfu.itis.csport.service.TeamService;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void create(Team team) throws Exception {
        try {
            teamRepository.save(team);
        } catch (Exception e) {
            throw new Exception();
        }

    }

    @Override
    @Modifying
    public void update(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void delete(Team team) {
        teamRepository.delete(team);
    }

    @Override
    public Team getOne(int id) {
        return teamRepository.findOne(id);
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
