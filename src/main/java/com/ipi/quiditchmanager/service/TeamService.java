package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.Team;

import java.util.List;

public interface TeamService {
    Team addTeam(Team team);
    List<Team> getTeams();
}
