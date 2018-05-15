package ru.kpfu.itis.csport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.model.Team;
import ru.kpfu.itis.csport.model.TournamentMatch;
import ru.kpfu.itis.csport.repository.TournamentMatchRepository;
import ru.kpfu.itis.csport.service.TournamentMatchService;

import java.util.Objects;

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

    @Override
    public void setWinner(TournamentMatch match, int winner) {
        match.setWinner(winner);

        if (match.getNextMatch() != null) {
            TournamentMatch nextMatch = match.getNextMatch();
            Team winnerTeam = match.getWinnerTeam();

            if (nextMatch.getTeam1() == null){
                nextMatch.setTeam1(winnerTeam);
                if(Objects.equals(nextMatch.getTeam2Winner(), 1)) {
                    //set win automatically
                    setWinner(nextMatch, 1);
                }
            }
            else{
                nextMatch.setTeam2(winnerTeam);
                if(Objects.equals(nextMatch.getTeam1Winner(), 2)) {
                    setWinner(nextMatch, 2);
                }
            }

            this.save(nextMatch);
        }
        this.save(match);
    }
}
