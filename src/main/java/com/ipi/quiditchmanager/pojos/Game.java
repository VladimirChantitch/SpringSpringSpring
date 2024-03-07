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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;
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

    public Game(Stadium stadium, List<Team> teams, Date date) {
        this.stadium = stadium;
        this.teams = teams;
        this.date = date;
    }
}
