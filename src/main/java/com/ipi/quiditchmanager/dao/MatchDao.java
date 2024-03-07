package com.ipi.quiditchmanager.dao;

import com.ipi.quiditchmanager.pojos.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchDao extends JpaRepository<Game, Long> {
}
