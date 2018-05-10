package ru.kpfu.itis.csport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.csport.model.Discipline;
import ru.kpfu.itis.csport.service.DisciplineService;

import java.util.List;

@Controller
public class DisciplinesController {
    @Autowired
    private DisciplineService disciplineService;

    @GetMapping(value = "disciplines")
    public String getDisciplines(Model model) {
        List<Discipline> disciplines = disciplineService.findAll();
        model.addAttribute("disciplines", disciplines);
        return "disciplines";
    }

    @PostMapping(value = "/disciplines/new")
    public String createDiscipline(@RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("players") int players) {
        Discipline discipline = new Discipline();
        discipline.setName(name);
        discipline.setDescription(description);
        discipline.setTeamSize(players);
        try {
            disciplineService.add(discipline);
        } catch (Exception e) {
            return "redirect:/disciplines?disciplineAlreadyExistError=true";
        }
        return "redirect:/disciplines";
    }


    @PostMapping(value = "/disciplines/update")
    public String updateDiscipline(@RequestParam("team_id") int id,
                                   @RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("players") int players) {
        Discipline discipline = disciplineService.find(id);
        discipline.setName(name);
        discipline.setDescription(description);
        discipline.setTeamSize(players);
        disciplineService.update(discipline);
        return "redirect:/disciplines";
    }

    @PostMapping(value = "/disciplines/{id}/delete")
    public String deleteDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.find(id);
        disciplineService.delete(discipline);
        return "redirect:/disciplines";
    }
}
