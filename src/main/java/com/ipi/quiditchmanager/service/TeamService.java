package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.Team;

import java.util.List;

public interface TeamService {
    Team addTeam(Team team);
    List<Team> getTeams();

    Team getTeamById(long id);
    Team getTeamByName(String name);

    void updateTeamDetails(Long id, String teamName, String countryName);

    void deleteById(Long id);

    void update(Team team);

    void create(String name, String country);

    Team create();
}
