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
        User user = (User)session.getAttribute("user");

        if (user != null){
            return "Home";
        }
        else
        {
            return "Home";
            //return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
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
    public String showMatches(Model model, @RequestParam(name = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        List<Game> games;
        if (date != null) {
            // Fetch games for the selected date
            games = matchService.getGamesByDate(date);
            for (Game game : games){
                System.out.println("Hello world  2"+game.getDate());
            }
        } else {
            // If no date is selected, fetch all games
            games = matchService.getGames();
        }
        model.addAttribute("matches", games);
        return "Matches";
    }


}