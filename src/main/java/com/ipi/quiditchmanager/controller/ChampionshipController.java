package com.ipi.quiditchmanager.controller;

import com.ipi.quiditchmanager.pojos.ChampionShip;
import com.ipi.quiditchmanager.pojos.Team;
import com.ipi.quiditchmanager.pojos.User;
import com.ipi.quiditchmanager.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChampionshipController {
    private ChampionshipService championshipService;
    private CountryService countryService;
    private MatchService matchService;
    private StadiumService stadiumService;
    private TeamService teamService;
    private UserService userService;

    public ChampionshipController(ChampionshipService championshipService,
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

    @GetMapping("/championships")
    public String championshipsPage(HttpSession session, Model model, @RequestParam(name = "championship", required = false) String championshipName) {
        model.addAttribute("loggedIn", userService.getIsLogged(session));
        model.addAttribute("isActive", 2);
        List<ChampionShip> champions = championshipService.getChampionShips();

        ChampionShip selectedChampionship = champions.get(0);
        if (championshipName != null) {
            for (ChampionShip championShip : champions) {
                if (championShip.getName().equals(championshipName)) {
                    selectedChampionship = championShip;
                    break;
                }
            }
        }

        model.addAttribute("championShips", champions);
        model.addAttribute("championShip", selectedChampionship);
        return "ChampionShips";
    }
}
