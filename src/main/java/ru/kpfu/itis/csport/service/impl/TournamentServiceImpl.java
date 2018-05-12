package ru.kpfu.itis.csport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.model.Team;
import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.model.TournamentMatch;
import ru.kpfu.itis.csport.model.TournamentRequest;
import ru.kpfu.itis.csport.repository.TournamentMatchRepository;
import ru.kpfu.itis.csport.repository.TournamentRepository;
import ru.kpfu.itis.csport.service.TournamentService;

import java.util.*;
import java.util.stream.Collectors;

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
    public void start(Tournament tournament) {
        if(tournament.getStatus() != UPCOMING) {
            throw new IllegalStateException("Tournament already started");
        }
        //generate matches
        List<Team> acceptedTeams = tournament.getRequests().stream()
                .filter(TournamentRequest::isAccepted)
                .map(TournamentRequest::getTeam)
                .collect(Collectors.toList());
        Collections.shuffle(acceptedTeams);

        List<TournamentMatch> firstLevelGames = new ArrayList<>();
        for (int i = 0; i < tournament.getTeamCount() / 2; i++) {
            TournamentMatch match = new TournamentMatch();
            match.setTournament(tournament);
            match.setRound(1);
            firstLevelGames.add(match);
        }

        for (int i = 0; i < firstLevelGames.size() && !acceptedTeams.isEmpty(); i++) {
            firstLevelGames.get(i).setTeam1(acceptedTeams.remove(0));
        }
        for (int i = 0; i < firstLevelGames.size() && !acceptedTeams.isEmpty(); i++) {
            firstLevelGames.get(i).setTeam2(acceptedTeams.remove(0));
        }

        Map<Integer, List<TournamentMatch>> rounds = new HashMap<>();
        rounds.put(1, firstLevelGames);

        for(int round = 2; 1 << round <= tournament.getTeamCount(); round++) {
            List<TournamentMatch> games = rounds.get(round-1);
            List<TournamentMatch> nextGames = new ArrayList<>();
            for (int i = 0; i < games.size(); i+=2) {
                TournamentMatch nextGame = new TournamentMatch();
                nextGame.setTournament(tournament);
                nextGame.setRound(round);

                TournamentMatch game1 = games.get(i);
                TournamentMatch game2 = games.get(i+1);

                if(game1.getTeam1() == null && game1.getTeam2() != null) {
                    game1.setWinner(2);
                    nextGame.setTeam1(game1.getTeam2());
                }
                else if(game1.getTeam2() == null && game1.getTeam1() != null) {
                    game1.setWinner(1);
                    nextGame.setTeam1(game1.getTeam1());
                }

                if(game2.getTeam1() == null && game2.getTeam2() != null) {
                    game2.setWinner(2);
                    nextGame.setTeam2(game2.getTeam2());
                }
                else if(game2.getTeam2() == null && game2.getTeam1() != null) {
                    game2.setWinner(1);
                    nextGame.setTeam2(game2.getTeam1());
                }

                game1.setNextMatch(nextGame);
                game2.setNextMatch(nextGame);

                nextGames.add(nextGame);
            }
            rounds.put(round, nextGames);
        }

        tournament.getMatches().addAll(
                rounds.values().stream()
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
        );
        tournament.setStatus(ACTIVE);
        tournamentRepository.save(tournament);
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
