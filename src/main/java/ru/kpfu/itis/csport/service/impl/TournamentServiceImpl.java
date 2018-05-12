package ru.kpfu.itis.csport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.repository.TournamentRepository;
import ru.kpfu.itis.csport.service.TournamentService;
import ru.kpfu.itis.csport.model.TournamentMatch;
import ru.kpfu.itis.csport.repository.TournamentMatchRepository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import static ru.kpfu.itis.csport.model.Tournament.Status.*;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:55 PM
 */
@Service
public class TournamentServiceImpl implements TournamentService {

    private TournamentRepository tournamentRepository;
    private TournamentMatchRepository tournamentMatchRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository,
                                 TournamentMatchRepository tournamentMatchRepository){
        this.tournamentRepository = tournamentRepository;
        this.tournamentMatchRepository = tournamentMatchRepository;
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
    public Tournament create(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament update(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament findById(int id) {
        return tournamentRepository.findOne(id);
    }

    @Override
    public Map<String, List<TournamentMatch>> getTournamentGrid(Tournament tournament) {
        Map<String, List<TournamentMatch>> grid = new HashMap<>();
        grid.put("1", tournamentMatchRepository.getAllByTournamentAndRoundOrderByNextMatchIdAsc(tournament, 1));

        for (int i=2; i<5; i++) {
            grid.put((String.valueOf(i)), new ArrayList<>());
            List<TournamentMatch> prevRound =  grid.get(String.valueOf(i-1));
            for (int j=0; j<prevRound.size(); j+=2){
                if (prevRound.get(j).getNextMatch() != null)
                    grid.get(String.valueOf(i)).add(prevRound.get(j).getNextMatch());
            }
        }
        return grid;
    }
}
