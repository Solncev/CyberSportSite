package ru.kpfu.itis.csport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.model.ComputerGame;
import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.repository.ComputerGameRepository;
import ru.kpfu.itis.csport.repository.TournamentRepository;
import ru.kpfu.itis.csport.service.TournamentService;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:55 PM
 */
@Service
public class TournamentServiceImpl implements TournamentService {

    private TournamentRepository tournamentRepository;
    private ComputerGameRepository computerGameRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository, ComputerGameRepository computerGameRepository) {
        this.tournamentRepository = tournamentRepository;
        this.computerGameRepository = computerGameRepository;
    }

    @Override
    public List<ComputerGame> getAllGames() {
        return computerGameRepository.findAll();
    }

    @Override
    public List<Tournament> getUpcoming() {
        return tournamentRepository.getAllByStatus("upcoming"); //todo
    }

    @Override
    public List<Tournament> getActive() {
        return tournamentRepository.getAllByStatus("active"); //todo
    }

    @Override
    public List<Tournament> getPast() {
        return tournamentRepository.getAllByStatus("past"); //todo
    }
}
