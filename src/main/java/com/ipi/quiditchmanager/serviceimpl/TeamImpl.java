package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.ChampionshipDao;
import com.ipi.quiditchmanager.dao.CountryDao;
import com.ipi.quiditchmanager.dao.MatchDao;
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
    @Autowired
    private MatchDao matchDao;
    @Autowired
    private ChampionshipDao championshipDao;
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
    @Override
    public void deleteById(Long id) {
        Team team = teamDao.findById(id).get();

        Country country = team.getCountry();
        if (team.getCountry() != null){
            List<Team> countryTeams = country.getTeams();
            countryTeams.remove((team));
            country.setTeams(countryTeams);
            countryDao.save(country);
        }

        team.getMatches().forEach(game -> {
            List<Team> gameTeams = game.getTeams();
            gameTeams.remove(team);
            game.setTeams(gameTeams);
            matchDao.save(game);
        });

        team.getChampionShips().forEach(championShip -> {
            List<Team> champTeams = championShip.getTeams();
            champTeams.remove(team);
            championShip.setTeams(champTeams);
            championshipDao.save(championShip);
        });

        teamDao.delete(team);
    }

    @Override
    public void update(Team team) {
        teamDao.save(team);
    }

    @Override
    public void create(String name, String country) {
        Team team = new Team();
        team.setName((name));
        team.setCountry(countryDao.findByName(country));
    }

    @Override
    public Team create() {
        Team team = teamDao.findByName("set a name please");
        if (team != null)
            return team;
        
        team = new Team();
        team.setName("set a name please");
        team.setCountry(countryDao.findByName("France"));
        teamDao.save(team);
        return teamDao.findByName("set a name please");
    }
}
