package ru.kpfu.itis.csport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.model.TournamentMatch;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.service.TournamentMatchService;
import ru.kpfu.itis.csport.service.TournamentService;
import ru.kpfu.itis.csport.service.UserService;

import java.util.Collection;
import java.util.List;

@AuthController
@RequestMapping(path = "/tournament_matches")
public class TournamentMatchController {

    private TournamentService tournamentService;
    private TournamentMatchService tournamentMatchService;
    private UserService userService;

    @Autowired
    public TournamentMatchController(TournamentService tournamentService,
                                     TournamentMatchService tournamentMatchService,
                                     UserService userService) {
        this.tournamentService = tournamentService;
        this.tournamentMatchService = tournamentMatchService;
        this.userService = userService;
   }

    @GetMapping("/{match_id}")
    public String matchPage(ModelMap modelMap,
                            @PathVariable("match_id") int matchId) {
        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        modelMap.addAttribute("currentUser", currentUser);

        TournamentMatch match = tournamentMatchService.getById(matchId);
        modelMap.addAttribute("match", match);

        Tournament tournament = match.getTournament();
        modelMap.addAttribute("tournament", tournament);

        List<TournamentMatch> tournamentMatches = tournament.getMatches();

        return "tournament_match";
    }


    @PostMapping("/{match_id}/attach")
    public String attachLink(@PathVariable("match_id") int matchId,
                             @RequestParam("link") String link) {

        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());

        TournamentMatch match = tournamentMatchService.getById(matchId);

        if (currentUser.getUsername().equals(match.getTeam1().getLeader().getUsername()) || currentUser.getUsername().equals(match.getTeam2().getLeader().getUsername())) {
            match.setDescription(link);
            tournamentMatchService.save(match);
        }

        return "redirect:/tournament_matches/" + matchId;
    }

    @PostMapping("/{match_id}/send_result")
    public String sendResult(@PathVariable("match_id") int matchId,
                             @RequestParam("result") String result) {

        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        TournamentMatch match = tournamentMatchService.getById(matchId);

        if (currentUser.getUsername().equals(match.getTeam1().getLeader().getUsername())) {
            if (result.equals("win"))
                match.setTeam1Winner(1);
            else
                match.setTeam1Winner(2);
        }

        if (currentUser.getUsername().equals(match.getTeam2().getLeader().getUsername())) {
            if (result.equals("win"))
                match.setTeam1Winner(2);
            else
                match.setTeam1Winner(1);
        }

        if (match.getTeam1Winner().equals(match.getTeam2Winner()))
            match.setWinner(match.getTeam1Winner());

        tournamentMatchService.save(match);
        return "redirect:/tournament_matches/" + matchId;
    }


    @PostMapping("/{match_id}/resolve_conflict")
    public String resolveConflict(@PathVariable("match_id") int matchId,
                             @RequestParam("result") int result) {
        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        TournamentMatch match = tournamentMatchService.getById(matchId);

        match.setWinner(result);

        tournamentMatchService.save(match);
        return "redirect:/tournament_matches/" + matchId;
    }
}
