package ru.kpfu.itis.csport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.service.TeamService;
import ru.kpfu.itis.csport.service.TournamentService;
import ru.kpfu.itis.csport.util.TournamentForm;
import ru.kpfu.itis.csport.util.TournamentTransformer;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:04 PM
 */
@AuthController
@RequestMapping(path = "/tournaments")
public class TournamentController {

    private TournamentService tournamentService;
    private TeamService teamService;
    private TournamentTransformer transformer;

    @Autowired
    public TournamentController(TournamentService tournamentService, TeamService teamService) {
        this.tournamentService = tournamentService;
        this.teamService = teamService;
        this.transformer = new TournamentTransformer(tournamentService);
    }

    @GetMapping({"", "/", "/all"})
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("all_games", tournamentService.getAllGames());

        modelMap.addAttribute("upcoming", processList(tournamentService.getUpcoming()))
                .addAttribute("active", processList(tournamentService.getActive()))
                .addAttribute("past", processList(tournamentService.getPast()));

        return "tournaments";
    }

    @GetMapping("/{id}")
    public String getOne(ModelMap map, @PathVariable("id") int id) {
        Tournament tournament = tournamentService.findById(id);
        //todo matches
        map.addAttribute("tournament", tournament);
        return "tournament";
    }

    @PostMapping({"/new", "/create"})
    public String create(@RequestBody @Valid TournamentForm form, BindingResult result, ModelMap modelMap) {
        if(result.hasErrors()) {
            modelMap.addAttribute("error", result.getAllErrors().get(0).getCode());
        }
        Tournament tournament = transformer.apply(form);
        tournamentService.create(tournament);
        return "tournaments";
    }

    private List<Tournament> processList(List<Tournament> source) {
        return source.stream()
                .sorted(Comparator.comparing(Tournament::getDate).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}
