package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.ChampionShip;
import com.ipi.quiditchmanager.pojos.Game;

import java.util.List;

public interface ChampionshipService {
    ChampionShip addChampionship(ChampionShip championShip);

    List<ChampionShip> getChampionShips();

    ChampionShip getChampionShipByName(String name);
}
