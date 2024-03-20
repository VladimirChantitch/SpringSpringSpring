package com.ipi.quiditchmanager.dao;

import com.ipi.quiditchmanager.pojos.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface MatchDao extends JpaRepository<Game, Long> {

    List<Game> getGameByDate(Date date);
}
