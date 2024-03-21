package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.ChampionShip;
import com.ipi.quiditchmanager.pojos.Game;
import com.ipi.quiditchmanager.pojos.Stadium;
import com.ipi.quiditchmanager.pojos.Team;

import java.util.Date;
import java.util.List;

public interface MatchService {
    Game addMatch(Game match);

    List<Game> getGames();

    List<Game> getGamesByDate(Date date);

    Game getMatchById(long id);

    Game create();

    void deleteById(Long id);

    Game findById(Long id);

    void updateGame(Game game);

    void create(Date date, Team team1, Team team2, Stadium stade, ChampionShip championShip);

    void updateTeamDetails(Long id, Date date, Team team1, Team team2, Stadium stade, ChampionShip championShip);
}
