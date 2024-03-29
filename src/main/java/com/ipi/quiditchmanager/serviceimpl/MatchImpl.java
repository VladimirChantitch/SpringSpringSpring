package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.ChampionshipDao;
import com.ipi.quiditchmanager.dao.MatchDao;
import com.ipi.quiditchmanager.dao.StadiumDao;
import com.ipi.quiditchmanager.dao.TeamDao;
import com.ipi.quiditchmanager.pojos.*;
import com.ipi.quiditchmanager.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MatchImpl implements MatchService {
    @Autowired
    private MatchDao matchDao;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private ChampionshipDao championshipDao;
    @Autowired
    private StadiumDao stadiumDao;

    @Override
    public Game addMatch(Game match) {
        return matchDao.save(match);
    }

    @Override
    public List<Game> getGames() {
        return matchDao.findAll();
    }

    @Override
    public List<Game> getGamesByDate(Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return matchDao.getGameByDate(sqlDate);
    }

    @Override
    public Game getMatchById(long id) {
        return matchDao.findById(id).get();
    }

    @Override
    public Game create() {
        Game game = new Game();
        game.setDate(new Date(2024 -1900, 11, 11));
        game.setChampionShip(championshipDao.findByName("derby"));
        game.setTeams(Arrays.asList(teamDao.findByName("Lancashire"), teamDao.findByName("Barnton")));
        game.setStadium(stadiumDao.findByName("Neuschwanstein Stadium"));
        matchDao.save(game);
        java.sql.Date sqlDate = new java.sql.Date(new Date(2024 -1900, 11, 11).getTime());
        return matchDao.getGameByDate(sqlDate).get(0);
    }

    public void deleteById(Long id) {
        Game game = matchDao.findById(id).get();

        List<Team> teams = game.getTeams();
        for (Team team : teams) {
            List<Game> teamGames = team.getMatches();
            teamGames.remove(game);
            team.setMatches(teamGames);
            teamDao.save(team);
        }
        ChampionShip champion = game.getChampionShip();
        List<Game> championshipGame = champion.getGames();
        championshipGame.remove((game));
        champion.setGames(championshipGame);
        championshipDao.save(champion);

        matchDao.delete(game);
    }

    public Game findById(Long id) {
        return findById(id);
    }

    @Override
    public void create(Date date, Team team1, Team team2, Stadium stade, ChampionShip championShip) {
        Date sqlDate = new java.sql.Date(date.getTime());
        Game game = new Game(stade, Arrays.asList(team1, team2), sqlDate, championShip);
        matchDao.save(game);
        List<Game> lisGames = championShip.getGames();
        lisGames.add(game);
        championShip.setGames(lisGames);
        championshipDao.save(championShip);
    }

    @Override
    public void updateTeamDetails(Long id, Date date, Team team1, Team team2, Stadium stade, ChampionShip championShip) {
        Game game = matchDao.findById(id).get();

        if (team1 != null && team2 != null && stade != null && date != null) {
            //game.setDate(d);
            List<Game> listGame = team1.getMatches();
            game.setTeams(Arrays.asList(team1, team2));
            game.setStadium(stade);
            game.setChampionShip(championShip);
            listGame.add(game);
            team1.setMatches(listGame);
            teamDao.save(team1);
            listGame = team2.getMatches();
            listGame.add(game);
            team2.setMatches(listGame);
            teamDao.save(team2);

            listGame = stade.getMatches();
            listGame.add(game);
            stade.setMatches(listGame);
            stadiumDao.save(stade);

            listGame = championShip.getGames();
            listGame.add(game);
            championShip.setGames(listGame);
            championshipDao.save(championShip);

            matchDao.save(game);
        }
    }

    public void updateGame(Game game) {
        matchDao.save(game);
    }
}
