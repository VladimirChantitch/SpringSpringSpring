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
                getLogged(session));
        model.addAttribute("isActive", 0);
        return "Home";
    }

    @GetMapping("/teams")
    public String teamsPage(HttpSession session, ModelMap model) {
        model.addAttribute("loggedIn",
                getLogged(session));
        model.addAttribute("isActive", 1);
        model.addAttribute("teams", teamService.getTeams());
        //model.addAttribute("navActivatedId", 5);
        return "Teams";
    }

    @GetMapping("/team")
    public String teamDetails(HttpSession session, ModelMap model, @RequestParam("id") Long id) {
        model.addAttribute("loggedIn",
                getLogged(session));
        model.addAttribute("isActive", -1);
        model.addAttribute("team", teamService.getTeamById(id));
        model.addAttribute("countries", countryService.getCountries());

        return "Team";
    }

    @PostMapping("/team/update")
    public String updateTeamDetails(@RequestParam("id") Long id,
                                    @RequestParam("name") String name,
                                    @RequestParam("country") String country){

        teamService.updateTeamDetails(id, name, country);
        return "redirect:/teams";
    }

    @PostMapping("/team/delete")
    public String updateTeamDetails(@RequestParam("id") Long id){
        teamService.deleteById(id);
        return "redirect:/teams";
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session, Model model) {
        model.addAttribute("loggedIn",
                getLogged(session));
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
            return "Login";
        }
    }

    @GetMapping("/matches")
    public String showMatches(HttpSession session, Model model, @RequestParam(name = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        model.addAttribute("loggedIn",
                getLogged(session));
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

    //@GetMapping("/championships")
    //public String championshipsPage(HttpSession session, Model model, @RequestParam(name = "championship", required = false) ChampionShip championship) {
    //    model.addAttribute("loggedIn",
    //            getLogged(session));
    //    model.addAttribute("isActive", 2);
    //    List<ChampionShip> champions = championshipService.getChampionShips();
//
    //    model.addAttribute("championShips", champions);
    //    model.addAttribute("championShip", championship);
    //    return "ChampionShips";
    //}

    @GetMapping("/championships")
    public String championshipsPage(HttpSession session, Model model, @RequestParam(name = "championship", required = false) String championshipName) {
        model.addAttribute("loggedIn", getLogged(session));
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

    private boolean getLogged(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user != null;
    }
}
