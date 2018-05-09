package ru.kpfu.itis.csport.service;

import ru.kpfu.itis.csport.model.TournamentMatch;

public interface TournamentMatchService {

    TournamentMatch getById(int id);

    void save(TournamentMatch match);

}
