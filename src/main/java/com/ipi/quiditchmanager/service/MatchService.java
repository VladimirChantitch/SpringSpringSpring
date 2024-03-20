package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.Game;

import java.util.Date;
import java.util.List;

public interface MatchService {
    Game addMatch(Game match);

    List<Game> getGames();

    List<Game> getGamesByDate(Date date);
}
