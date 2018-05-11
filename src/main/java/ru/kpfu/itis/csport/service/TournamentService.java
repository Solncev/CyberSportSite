package ru.kpfu.itis.csport.service;

import java.util.List;

import ru.kpfu.itis.csport.model.Tournament;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:13 PM
 */
public interface TournamentService {

    List<Tournament> getUpcoming();
    List<Tournament> getActive();
    List<Tournament> getPast();

    Tournament create(Tournament tournament);

    Tournament update(Tournament tournament);

    Tournament findById(int id);
}
