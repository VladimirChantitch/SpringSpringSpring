package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.CountryDao;
import com.ipi.quiditchmanager.dao.TeamDao;
import com.ipi.quiditchmanager.pojos.Country;
import com.ipi.quiditchmanager.pojos.Team;
import com.ipi.quiditchmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private CountryDao countryDao;
    @Override
    public Team addTeam(Team team) {
        return teamDao.save(team);
    }

    @Override
    public List<Team> getTeams() {
        return teamDao.findAll();
    }

    @Override
    public Team getTeamById(long id) {
        return teamDao.findById(id).get();
    }

    @Override
    public void updateTeamDetails(Long id, String teamName, String countryName) {
        Team team = teamDao.findById(id).get();
        Country country = countryDao.findByName(countryName);

        team.setName(teamName);
        if (country != null)
            team.setCountry(country);

        teamDao.save(team);
    }
}
