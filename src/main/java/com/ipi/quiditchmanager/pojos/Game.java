package com.ipi.quiditchmanager.pojos;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Stadium stadium;
    @ManyToMany
    private List<Team> teams;

    @ManyToOne
    private ChampionShip championShip;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Long getId() {
        return id;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Game() {
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ChampionShip getChampionShip() {
        return championShip;
    }

    public void setChampionShip(ChampionShip championShip) {
        this.championShip = championShip;
    }

    public Game(Stadium stadium, List<Team> teams, Date date, ChampionShip championShip) {
        this.stadium = stadium;
        this.teams = teams;
        this.date = date;
        this.championShip = championShip;
    }
}
