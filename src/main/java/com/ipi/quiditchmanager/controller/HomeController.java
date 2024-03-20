package com.ipi.quiditchmanager.controller;

import com.ipi.quiditchmanager.pojos.*;
import com.ipi.quiditchmanager.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

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
            model.addAttribute("loggedIn", true);
            return "Home";
        }
        else
        {
            model.addAttribute("loggedIn", false);
            return "Home";
        }
    }

    @GetMapping("/teams")
    public String teamsPage() {
        return "Teams";
    }

    @GetMapping("/championships")
    public String championshipsPage() {
        return "Championships";
    }

    @GetMapping("/games")
    public String gamesPage() {
        return "Games";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loggedIn", false);
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
}
