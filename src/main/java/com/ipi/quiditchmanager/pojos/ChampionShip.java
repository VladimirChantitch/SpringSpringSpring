package com.ipi.quiditchmanager.pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class ChampionShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Championship name is required")
    private String name;
    private String logoFileName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    @NotEmpty(message = "Ranking Type is required")
    private String rankingType;
    private float bestGoldenSnitchCatchTime;
    private int maxQuaffleMarked;
    @ManyToMany
    private List<Country> participatingCountries;

    @ManyToMany
    private List<Team> teams;

    @OneToMany
    private List<Game> games;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRankingType() {
        return rankingType;
    }

    public void setRankingType(String rankingType) {
        this.rankingType = rankingType;
    }

    public float getBestGoldenSnitchCatchTime() {
        return bestGoldenSnitchCatchTime;
    }

    public void setBestGoldenSnitchCatchTime(float bestGoldenSnitchCatchTime) {
        this.bestGoldenSnitchCatchTime = bestGoldenSnitchCatchTime;
    }

    public int getMaxQuaffleMarked() {
        return maxQuaffleMarked;
    }

    public void setMaxQuaffleMarked(int maxQuaffleMarked) {
        this.maxQuaffleMarked = maxQuaffleMarked;
    }

    public List<Country> getParticipatingCountries() {
        return participatingCountries;
    }

    public void setParticipatingCountries(List<Country> participatingCountries) {
        this.participatingCountries = participatingCountries;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public ChampionShip() {
    }

    public ChampionShip(String name,
                        String logoFileName,
                        Date startDate,
                        Date endDate,
                        String rankingType,
                        float bestGoldenSnitchCatchTime,
                        int maxQuaffleMarked,
                        List<Country> participatingCountries,
                        List<Team> teams,
                        List<Game> games) {
        this.name = name;
        this.logoFileName = logoFileName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rankingType = rankingType;
        this.bestGoldenSnitchCatchTime = bestGoldenSnitchCatchTime;
        this.maxQuaffleMarked = maxQuaffleMarked;
        this.participatingCountries = participatingCountries;
        this.teams = teams;
        this.games = games;
    }
}
