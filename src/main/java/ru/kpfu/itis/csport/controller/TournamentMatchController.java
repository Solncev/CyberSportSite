package ru.kpfu.itis.csport.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.model.TournamentMatch;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.service.TournamentMatchService;
import ru.kpfu.itis.csport.service.TournamentService;
import ru.kpfu.itis.csport.service.UserService;

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

        Map<String,List<TournamentMatch>> matchesGrid = tournamentService.getTournamentGrid(tournament);
        modelMap.addAttribute("matches_grid", matchesGrid);
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
    @Transactional
    public String sendResult(@PathVariable("match_id") int matchId,
                             @RequestParam("result") int result) {

        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        TournamentMatch match = tournamentMatchService.getById(matchId);

        System.out.println("\n0\n");
        if (currentUser.getUsername().equals(match.getTeam1().getLeader().getUsername())) {
            match.setTeam1Winner(result);
            System.out.println("\n0\n");
        }
        else if (currentUser.getUsername().equals(match.getTeam2().getLeader().getUsername())) {
            match.setTeam2Winner(result);
            System.out.println("\n0\n");
        }
        tournamentMatchService.save(match);

        if (match.getTeam1Winner().equals(match.getTeam2Winner())) {
            System.out.println("\n1\n");
            tournamentMatchService.setWinner(match, match.getTeam1Winner());
        }

        boolean allPlayed = match.getTournament().getMatches().stream()
            .allMatch(tournamentMatch -> tournamentMatch.getWinner() == null || tournamentMatch.getWinner() == 0);
        if(allPlayed) {
          match.getTournament().setStatus(Tournament.Status.PAST);
          tournamentService.update(match.getTournament());
        }

        return "redirect:/tournament_matches/" + matchId;
    }


    @PostMapping("/{match_id}/resolve_conflict")
    public String resolveConflict(  @PathVariable("match_id") int matchId,
                                    @RequestParam("result") int result) {

        TournamentMatch match = tournamentMatchService.getById(matchId);
        tournamentMatchService.setWinner(match, result);

        return "redirect:/tournament_matches/" + matchId;
    }
}
