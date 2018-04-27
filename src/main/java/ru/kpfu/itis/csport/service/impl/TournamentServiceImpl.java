package ru.kpfu.itis.csport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.model.ComputerGame;
import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.repository.ComputerGameRepository;
import ru.kpfu.itis.csport.repository.TournamentRepository;
import ru.kpfu.itis.csport.service.TournamentService;

import java.util.List;

import static ru.kpfu.itis.csport.model.Tournament.Status.*;

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
        return tournamentRepository.getAllByStatus(UPCOMING);
    }

    @Override
    public List<Tournament> getActive() {
        return tournamentRepository.getAllByStatus(ACTIVE);
    }

    @Override
    public List<Tournament> getPast() {
        return tournamentRepository.getAllByStatus(PAST);
    }

  @Override
  public Tournament findById(int id) {
    return tournamentRepository.findOne(id);
  }
}
