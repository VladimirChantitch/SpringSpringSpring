package com.ipi.quiditchmanager.pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Team name is required")
    private String name;
    @ManyToMany
    private List<ChampionShip> championShips;
    @ManyToMany
    private List<Game> matches;

    @ManyToOne
    private Country country;

    public Team(String name, List<ChampionShip> championShips, List<Game> matches, Country country) {
        this.name = name;
        this.championShips = championShips;
        this.matches = matches;
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChampionShip> getChampionShips() {
        return championShips;
    }

    public void setChampionShips(List<ChampionShip> championShips) {
        this.championShips = championShips;
    }

    public List<Game> getMatches() {
        return matches;
    }

    public void setMatches(List<Game> matches) {
        this.matches = matches;
    }

    public Team() {
    }

    public Long getId() {
        return id;
    }
}
