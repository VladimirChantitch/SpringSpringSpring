package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.Team;

import java.util.List;

public interface TeamService {
    Team addTeam(Team team);
    List<Team> getTeams();

    Team getTeamById(long id);

    void updateTeamDetails(Long id, String teamName, String countryName);
}
