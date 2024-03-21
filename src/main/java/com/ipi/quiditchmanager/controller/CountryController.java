package com.ipi.quiditchmanager.controller;

import com.ipi.quiditchmanager.pojos.ChampionShip;
import com.ipi.quiditchmanager.pojos.Country;
import com.ipi.quiditchmanager.pojos.Team;
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
public class CountryController {
    private ChampionshipService championshipService;
    private CountryService countryService;
    private MatchService matchService;
    private StadiumService stadiumService;
    private TeamService teamService;
    private UserService userService;

    public CountryController(ChampionshipService championshipService,
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

    @PostMapping("/country/delete")
    public String updateTeamDetails(@RequestParam("id") Long id){
        countryService.deleteById(id);

        return "redirect:/championships";
    }

    @GetMapping("/country")
    public String newCountry(HttpSession session, ModelMap model){

        model.addAttribute("loggedIn", userService.getIsLogged(session));
        model.addAttribute("isActive", -1);
        Country newCountry = countryService.create();
        model.addAttribute("country", newCountry);

        return "Country";
    }

    @PostMapping("/country/create")
    public String createCountry(HttpSession session, ModelMap model,
                                @RequestParam("id") Long id,
                                @RequestParam("name") String countryName,
                                @RequestParam("champId") Long champId){

        model.addAttribute("loggedIn",
                            userService.getIsLogged(session));
        model.addAttribute("isActive", -1);

        countryService.updateCountry(id, countryName);

        return "redirect:/championships";
    }
}
