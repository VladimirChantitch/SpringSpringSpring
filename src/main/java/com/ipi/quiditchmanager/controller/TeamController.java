package com.ipi.quiditchmanager.controller;

import com.ipi.quiditchmanager.pojos.ChampionShip;
import com.ipi.quiditchmanager.pojos.Game;
import com.ipi.quiditchmanager.pojos.Team;
import com.ipi.quiditchmanager.pojos.User;
import com.ipi.quiditchmanager.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class TeamController {
    private ChampionshipService championshipService;
    private CountryService countryService;
    private MatchService matchService;
    private StadiumService stadiumService;
    private TeamService teamService;
    private UserService userService;

    public TeamController(ChampionshipService championshipService,
                          CountryService countryService,
                          MatchService matchService,
                          StadiumService stadiumService,
                          TeamService teamService,
                          UserService userService
    ) {
        this.championshipService = championshipService;
        this.countryService = countryService;
        this.matchService = matchService;
        this.stadiumService = stadiumService;
        this.teamService = teamService;
        this.userService = userService;
    }

    @GetMapping("/teams")
    public String teamsPage(HttpSession session, ModelMap model) {
        model.addAttribute("loggedIn",
                userService.getIsLogged(session));
        model.addAttribute("isActive", 1);
        model.addAttribute("teams", teamService.getTeams());

        return "Teams";
    }

    @GetMapping("/team")
    public String teamDetails(HttpSession session, ModelMap model, @RequestParam("id") Long id) {
        model.addAttribute("loggedIn",
                userService.getIsLogged(session));
        model.addAttribute("isActive", -1);
        if (id >= 0)
            model.addAttribute("team", teamService.getTeamById(id));
        else{
            Team newTeam = teamService.create();
            model.addAttribute("team", newTeam);
        }
        model.addAttribute("countries", countryService.getCountries());

        return "Team";
    }

    @PostMapping("/team/update")
    public String updateTeamDetails(@RequestParam("id") Long id,
                                    @RequestParam("name") String name,
                                    @RequestParam("country") String country){
        if (id == -1)
            teamService.create(name, country);
        else
            teamService.updateTeamDetails(id, name, country);

        return "redirect:/teams";
    }

    @PostMapping("/team/delete")
    public String updateTeamDetails(@RequestParam("id") Long id){
        teamService.deleteById(id);

        return "redirect:/teams";
    }

    @PostMapping("/team/create")
    public String createTeam(){
        return "redirect:/team?id=-1";
    }
}
