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

@AuthController
@RequestMapping(path = "/tournament_matchs")
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

        return "redirect:/tournament_matchs/" + matchId;
    }
}
