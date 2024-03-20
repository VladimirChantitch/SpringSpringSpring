package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.TeamDao;
import com.ipi.quiditchmanager.pojos.Team;
import com.ipi.quiditchmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;
    @Override
    public Team addTeam(Team team) {
        return teamDao.save(team);
    }

    @Override
    public List<Team> getTeams() {
        return teamDao.findAll();
    }
}
