package ru.kpfu.itis.csport.service;

import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.model.TournamentMatch;

import java.util.List;
import java.util.Map;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:13 PM
 */
public interface TournamentService {

    List<Tournament> getUpcoming();
    List<Tournament> getActive();
    List<Tournament> getPast();

    void start(Tournament tournament);

    Tournament create(Tournament tournament);

    Tournament update(Tournament tournament);

    Tournament findById(int id);
    Map<String, List<TournamentMatch>> getTournamentGrid(Tournament tournament);
}
