package ru.kpfu.itis.csport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.csport.model.Team;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.service.TeamService;
import ru.kpfu.itis.csport.service.UserService;

@AuthController
public class TeamController {
    private final UserService userService;
    private final TeamService teamService;

    @Autowired
    public TeamController(UserService userService, TeamService teamService) {
        this.userService = userService;
        this.teamService = teamService;
    }

    @GetMapping(value = "/teams")
    public String getTeamsPage(Model model,
                               @RequestParam(value = "teamAlreadyExistError", required = false) String teamAlreadyExistError) {
        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("currentUser", currentUser);
        if (teamAlreadyExistError != null) {
            model.addAttribute("teamAlreadyExistError", teamAlreadyExistError);
        }
        return "teams";
    }

    @GetMapping(value = "/teams/check")
    @ResponseBody
    public boolean checkTeamExists(Model model,
                               @RequestParam(value = "name") String teamName) {
        Team team = teamService.getByName(teamName.toLowerCase());
        return team == null;
    }

    @PostMapping(value = "/teams/new")
    public String newTeam(@RequestParam("teamName") String teamName,
                          @RequestParam("player1") String player1,
                          @RequestParam("player2") String player2,
                          @RequestParam("player3") String player3,
                          @RequestParam("player4") String player4) {
        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Team team = new Team();
        team.setName(teamName);
        team.setFirstPlayer(player1);
        team.setSecondPlayer(player2);
        team.setThirdPlayer(player3);
        team.setFourthPlayer(player4);
        team.setLeader(currentUser);
        try {
            teamService.create(team);
        } catch (Exception e) {
            return "redirect:/teams?teamAlreadyExistError=true";
        }
        return "redirect:/teams";
    }


    @PostMapping(value = "/teams/update")
    public String updateTeam(@RequestParam("teamId") int teamId,
                             @RequestParam("teamName") String teamName,
                             @RequestParam("player1") String player1,
                             @RequestParam("player2") String player2,
                             @RequestParam("player3") String player3,
                             @RequestParam("player4") String player4) {
        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Team team = teamService.getOne(teamId);
        team.setName(teamName);
        team.setFirstPlayer(player1);
        team.setSecondPlayer(player2);
        team.setThirdPlayer(player3);
        team.setFourthPlayer(player4);
        team.setLeader(currentUser);
        try {
            teamService.update(team);
        } catch (Exception e) {
            return "redirect:/teams?teamAlreadyExistError=true";
        }
        return "redirect:/teams";
    }

    @PostMapping(value = "/teams/delete/{id}")
    public String deleteTeam(@PathVariable("id") int id) {
        Team team = teamService.getOne(id);
        teamService.delete(team);
        return "redirect:/teams";
    }
}
