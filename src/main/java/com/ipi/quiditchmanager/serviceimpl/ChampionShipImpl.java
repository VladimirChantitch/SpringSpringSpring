package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.ChampionshipDao;
import com.ipi.quiditchmanager.pojos.ChampionShip;
import com.ipi.quiditchmanager.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionShipImpl implements ChampionshipService {
    @Autowired
    private ChampionshipDao championshipDao;
    @Override
    public ChampionShip addChampionship(ChampionShip championShip) {
        return  championshipDao.save(championShip);
    }
    @Override
    public List<ChampionShip> getChampionShips() {
        return  championshipDao.findAll();
    }
}
