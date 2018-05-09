package ru.kpfu.itis.csport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.model.TournamentMatch;
import ru.kpfu.itis.csport.service.TournamentService;

@AuthController
@RequestMapping(path = "/tournament_matchs")
public class TournamentMatchController {

    private TournamentService tournamentService;

    @Autowired
    public TournamentMatchController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
   }

    @GetMapping("/{match_id}")
    public String matchPage(    ModelMap modelMap,
                                @PathVariable("match_id") int matchId) {

        TournamentMatch match = tournamentService.getMatchById(matchId);
        Tournament tournament = match.getTournament();
        modelMap.addAttribute("match", match);
        modelMap.addAttribute("tournament", tournament);
        return "tournament_match";
    }
}
