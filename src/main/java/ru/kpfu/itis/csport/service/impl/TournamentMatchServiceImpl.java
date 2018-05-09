package ru.kpfu.itis.csport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.model.TournamentMatch;
import ru.kpfu.itis.csport.repository.TournamentMatchRepository;
import ru.kpfu.itis.csport.service.TournamentMatchService;

@Service
public class TournamentMatchServiceImpl implements TournamentMatchService {

    private TournamentMatchRepository tournamentMatchRepository;

    @Autowired
    public TournamentMatchServiceImpl(TournamentMatchRepository tournamentMatchRepository) {
        this.tournamentMatchRepository = tournamentMatchRepository;
    }
    @Override
    public TournamentMatch getById(int id) {
        return tournamentMatchRepository.findOne(id);
    }

    @Override
    public void save(TournamentMatch match){
        tournamentMatchRepository.save(match);
    }
}
