package com.ipi.quiditchmanager.controller;

import com.ipi.quiditchmanager.pojos.*;
import com.ipi.quiditchmanager.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    private ChampionshipService championshipService;
    private CountryService countryService;
    private MatchService matchService;
    private StadiumService stadiumService;
    private TeamService teamService;
    private UserService userService;

    public HomeController(ChampionshipService championshipService,
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

    @GetMapping(value = "/")
    public String home(HttpSession session, ModelMap model) {
        model.addAttribute("loggedIn",
                userService.getIsLogged(session));
        model.addAttribute("isActive", 0);
        return "Home";
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session, Model model) {
        model.addAttribute("loggedIn",
                userService.getIsLogged(session));
        model.addAttribute("isActive", 4);
        model.addAttribute("user", new User());
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        User user = userService.authenticateUser(username, password);

        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            model.addAttribute("user", new User());
            model.addAttribute("loggedIn",
                    userService.getIsLogged(session));
            model.addAttribute("isActive", 4);
            return "Login";
        }
    }

    @GetMapping("/matches")
    public String showMatches(HttpSession session, Model model, @RequestParam(name = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        model.addAttribute("loggedIn",
                userService.getIsLogged(session));
        model.addAttribute("isActive", 3);
        List<Game> games;
        if (date != null) {
            // Fetch games for the selected date
            games = matchService.getGamesByDate(date);
        } else {
            // If no date is selected, fetch all games
            games = matchService.getGames();
        }
        model.addAttribute("matches", games);
        return "Matches";
    }

    @PostMapping("/matches/delete")
    public String updateMatchesDetails(@RequestParam("id") Long id){
        matchService.deleteById(id);
        return "redirect:/matches";
    }

    @PostMapping("/match/create")
    public String createMatch(){
        return "redirect:/match?id=-1";
    }
    @GetMapping("/match")
    public String matchDetails(HttpSession session, ModelMap model, @RequestParam("id") Long id) {
        model.addAttribute("loggedIn",
                userService.getIsLogged(session));
        model.addAttribute("isActive", -1);
        if (id >= 0)
            model.addAttribute("match", matchService.getMatchById(id));
        else{
            Game newGame = matchService.create();
            model.addAttribute("match", newGame);
        }
        model.addAttribute("teams", teamService.getTeams());
        model.addAttribute("stadiums", stadiumService.getStadium());
        model.addAttribute("championships", championshipService.getChampionShips());

        return "Match";
    }

    @PostMapping("/match/update")
    public String updateMatchDetails(@RequestParam("id") Long id,
                                    @RequestParam(name = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                    @RequestParam("teamName1") String teamName1,
                                    @RequestParam("teamName2") String teamName2,
                                    @RequestParam("stadeName") String stadeName,
                                    @RequestParam("championShipName") String championShipName){
        Team team1 = teamService.getTeamByName(teamName1);
        Team team2 = teamService.getTeamByName(teamName2);
        Stadium stade = stadiumService.findStadiumByName(stadeName);
        ChampionShip championShip = championshipService.getChampionShipByName(championShipName);

        matchService.updateTeamDetails(id, date, team1, team2, stade, championShip);

        return "redirect:/ChampionShips";
    }
}
