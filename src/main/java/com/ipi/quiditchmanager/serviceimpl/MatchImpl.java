package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.MatchDao;
import com.ipi.quiditchmanager.pojos.Game;
import com.ipi.quiditchmanager.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MatchImpl implements MatchService {
    @Autowired
    private MatchDao matchDao;
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

        //return toto;
        //List<Game> gamesByDate = new ArrayList<Game>();
        //for (Game game : getGames()) {
        //System.out.println("Hello world  "+date);
        //System.out.println("Hello world  2"+game.getDate());
        //    if (game.getDate().equals(date)) {
        //System.out.println("Hello world  3"+date);
        //        gamesByDate.add(game);
        //    }
        //}
        //return gamesByDate;
    }
}
