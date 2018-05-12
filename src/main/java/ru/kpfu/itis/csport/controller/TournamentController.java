package ru.kpfu.itis.csport.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.csport.model.Team;
import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.model.TournamentRequest;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.service.DisciplineService;
import ru.kpfu.itis.csport.service.TeamService;
import ru.kpfu.itis.csport.service.TournamentService;
import ru.kpfu.itis.csport.util.TournamentApplicationForm;
import ru.kpfu.itis.csport.util.TournamentForm;
import ru.kpfu.itis.csport.util.TournamentTransformer;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:04 PM
 */
@AuthController
@RequestMapping(path = "/tournaments")
public class TournamentController {

    private TeamService teamService;
    private TournamentService tournamentService;
    private DisciplineService disciplineService;

    private TournamentTransformer transformer;

    @Autowired
    public TournamentController(TeamService teamService, TournamentService tournamentService, DisciplineService disciplineService) {
        this.teamService = teamService;
        this.tournamentService = tournamentService;
        this.transformer = new TournamentTransformer(disciplineService);
        this.disciplineService = disciplineService;
    }

    @GetMapping({"", "/", "/all"})
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("all_disciplines", disciplineService.findAll());

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

    @GetMapping("/{id}/requests")
    public String requests(ModelMap map, @PathVariable("id") int id) {
        Tournament tournament = tournamentService.findById(id);
        map.addAttribute("tournament", tournament);
        map.addAttribute("requests", tournament.getRequests());
        return "tournament_requests";
    }

    @PostMapping("/{id}/start")
    public String start(ModelMap map, @PathVariable("id") int id, @RequestParam Map<String, String> allParams) {
        Tournament tournament = tournamentService.findById(id);
        List<Integer> acceptedIds = allParams.keySet().stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        tournament.setStatus(Tournament.Status.ACTIVE);
        tournament.getRequests().forEach(request -> {
            if(acceptedIds.contains(request.getTeam().getId())) {
                request.setAccepted(true);
            }
        });
        tournamentService.update(tournament);
        return "redirect:/tournaments";
    }

    @PostMapping("/{id}/finish")
    public String finish(@PathVariable("id") int id) {
        Tournament tournament = tournamentService.findById(id);
        tournament.setStatus(Tournament.Status.PAST);
        //todo check that its possible
        tournamentService.update(tournament);
        return "redirect:/tournaments";
    }

    @PostMapping({"/new", "/create"})
    public String create(@ModelAttribute("form") @Valid TournamentForm form, BindingResult result, ModelMap modelMap) {
        if(result.hasErrors()) {
            modelMap.addAttribute("creationError", result.getAllErrors().get(0).getCode());
        }
        Tournament tournament = transformer.apply(form);
        tournamentService.create(tournament);
        return "redirect:/tournaments";
    }

    @PostMapping({"/apply"})
    public String apply(@ModelAttribute("form") @Valid TournamentApplicationForm form, BindingResult result,
                        ModelMap modelMap) {
        Team team;
        if(form.getTeamId() == -1) {
            team = new Team();
            team.setName(form.getTeamName());
            team.setFirstPlayer(form.getPlayer1());
            team.setSecondPlayer(form.getPlayer2());
            team.setThirdPlayer(form.getPlayer3());
            team.setFourthPlayer(form.getPlayer4());
            team.setLeader(((User) modelMap.get("currentUser")));
            team = teamService.create(team);
        } else {
            team = teamService.getOne(form.getTeamId());
        }

        Tournament tournament = tournamentService.findById(form.getTournamentId());

        TournamentRequest request = new TournamentRequest();
        request.setTeam(team);
        request.setTournament(tournament);

        tournament.getRequests().add(request);
        tournamentService.update(tournament);

        return "redirect:/tournaments";
    }

    private List<Tournament> processList(List<Tournament> source) {
        return source.stream()
                .sorted(Comparator.comparing(Tournament::getDate).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}
