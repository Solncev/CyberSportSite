package ru.kpfu.itis.csport.service;

import ru.kpfu.itis.csport.model.ComputerGame;
import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.model.TournamentMatch;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:13 PM
 */
public interface TournamentService {

    List<ComputerGame> getAllGames();
    ComputerGame getGameById(int id);

    List<Tournament> getUpcoming();
    List<Tournament> getActive();
    List<Tournament> getPast();

    Tournament create(Tournament tournament);

    Tournament findById(int id);

    TournamentMatch getMatchById(int id);
}
