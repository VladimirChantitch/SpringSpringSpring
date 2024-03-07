package com.ipi.quiditchmanager.controller;

import com.ipi.quiditchmanager.pojos.*;
import com.ipi.quiditchmanager.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class LocalDBInitializerController {
    private ChampionshipService championshipService;
    private CountryService countryService;
    private MatchService matchService;
    private StadiumService stadiumService;
    private TeamService teamService;
    private UserService userService;


    public LocalDBInitializerController(ChampionshipService championshipService,
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

    @PostConstruct
    private void init(){
        User grogan = new User("GroStump", "Nimbus1811", "Grogan Stump", true);
        User cornelius  = new User("CornBus", "IAmTheMightyOne9999", "Cornelius Fudge", false);
        User dobby = new User("Chaussette", "123", "Dobby free-elve", false);

        Country france = new Country("France", "french_flag.jpg", null);
        Country england = new Country("England", "english_flag.jpg",null);
        Country irland = new Country("Irland", "irish_flag.jpg", null);
        Country bulgaria = new Country("Bulgaria", "bulgarian_flag.jpg",null);
        Country luxembourg = new Country("Luxembourg", "luxembourg_flag.jpg", null);
        Country norway = new Country("Norway", "norway_flag.jpg", null);

        Team quiberonQuafflePunchers = new Team("Quiberon Quafflepunchers", null, null, france);
        Team barnton = new Team("Barnton", null,null,england);
        Team lancashire = new Team("Lancashire", null,null, england);
        Team bigonVillebombers = new Team("Bigonville Bombers", null, null, luxembourg);
        Team cork = new Team("Cork", null, null, irland);
        Team vratsaVultures = new Team("Vratsa Vultures", null, null, bulgaria);
        Team karasjokKites = new Team("Karasjok Kites", null, null, norway);
        Team beauxBatons = new Team("Beaux Batons", null, null, france);

// Create and persist countries
        countryService.addCountry(france);
        countryService.addCountry(england);
        countryService.addCountry(irland);
        countryService.addCountry(luxembourg);
        countryService.addCountry(bulgaria);
        countryService.addCountry(norway);

// Create and persist stadiums
        Stadium trilleniumStadium = new Stadium("Trillenium Stadium",
                100000,
                "0635229674",
                null
        );
        Stadium germanNational = new Stadium("Neuschwanstein Stadium",
                50000,
                "0635229674",
                null
        );
        stadiumService.addStadium(trilleniumStadium);
        stadiumService.addStadium(germanNational);

// Create and persist users
        userService.addUser(grogan);
        userService.addUser(cornelius);
        userService.addUser(dobby);

// Create and persist teams
        teamService.addTeam(quiberonQuafflePunchers);
        teamService.addTeam(barnton);
        teamService.addTeam(lancashire);
        teamService.addTeam(bigonVillebombers);
        teamService.addTeam(cork);
        teamService.addTeam(vratsaVultures);
        teamService.addTeam(karasjokKites);
        teamService.addTeam(beauxBatons);

// Assign teams to countries
        france.setTeams(Arrays.asList(quiberonQuafflePunchers, beauxBatons));
        england.setTeams(Arrays.asList(barnton, lancashire));
        irland.setTeams(Collections.singletonList(cork));
        luxembourg.setTeams(Collections.singletonList(bigonVillebombers));
        bulgaria.setTeams(Collections.singletonList(vratsaVultures));
        norway.setTeams(Collections.singletonList(karasjokKites));

// Create and persist championship
        ChampionShip europeChampionShip = new ChampionShip("Europ Quiditch Cup",
                "europ_cup.logo",
                new Date(2024, 6, 12),
                new Date(2024, 7, 20),
                "tournament",
                100000000,
                0,
                Arrays.asList(france, england, irland, luxembourg, bulgaria, norway),
                Arrays.asList(quiberonQuafflePunchers, barnton, lancashire, bigonVillebombers, cork, vratsaVultures, karasjokKites, beauxBatons)
        );

        championshipService.addChampionship(europeChampionShip);

// Create and persist matches
        Game quarter0Match = new Game(trilleniumStadium, Arrays.asList(quiberonQuafflePunchers, barnton), new Date(2024, 6, 12));
        Game quarter1Match = new Game(germanNational, Arrays.asList(lancashire, bigonVillebombers),new Date(2024, 6, 14));
        Game quarter2Match = new Game(trilleniumStadium, Arrays.asList(cork, vratsaVultures), new Date(2024, 6, 16));
        Game quarter3Match = new Game(germanNational, Arrays.asList(karasjokKites, beauxBatons), new Date(2024, 6, 18));

        matchService.addMatch(quarter0Match);
        matchService.addMatch(quarter1Match);
        matchService.addMatch(quarter2Match);
        matchService.addMatch(quarter3Match);
    }
}
