package ru.kpfu.itis.csport.util;

import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.service.DisciplineService;

import java.util.function.Function;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/28/18 5:35 PM
 */
public class TournamentTransformer implements Function<TournamentForm, Tournament> {

    private DisciplineService disciplineService;

    public TournamentTransformer(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @Override
    public Tournament apply(TournamentForm form) {
        Tournament t = new Tournament();
        t.setName(form.getName());
        t.setDescription(form.getDescription());
        t.setDate(form.getStartDate());
        t.setDiscipline(disciplineService.find(form.getDiscipline()));
        t.setTeamCount(32); //todo
        return t;
    }
}
