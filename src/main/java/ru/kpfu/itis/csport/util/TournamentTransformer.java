package ru.kpfu.itis.csport.util;

import ru.kpfu.itis.csport.model.Tournament;
import ru.kpfu.itis.csport.service.TournamentService;

import java.util.function.Function;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/28/18 5:35 PM
 */
public class TournamentTransformer implements Function<TournamentForm, Tournament> {

    private TournamentService tournamentService;

    public TournamentTransformer(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Override
    public Tournament apply(TournamentForm form) {
        Tournament t = new Tournament();
        t.setName(form.getName());
        t.setDescription(form.getDescription());
        t.setDate(form.getStartDate());
        t.setGame(tournamentService.getGameById(form.getGame()));
        return t;
    }
}
