package com.ipi.quiditchmanager.dao;

import com.ipi.quiditchmanager.pojos.ChampionShip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipDao extends JpaRepository<ChampionShip, Long> {
    ChampionShip findByName(String name);
}
