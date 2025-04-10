package com.example.lab2.dto;

import com.example.lab2.models.Player;
import com.example.lab2.models.Team;

import java.util.Set;

public class TeamCreateDTO {
    String teamName;
    String coachName;
    String sponsorsName;
    String league;
    int foundingYear;

    public TeamCreateDTO(String teamName, String coachName, String sponsorsName, String league, int foundingYear) {
        this.teamName = teamName;
        this.coachName = coachName;
        this.sponsorsName = sponsorsName;
        this.league = league;
        this.foundingYear = foundingYear;
    }
    public Team convertTeamDTOtoTeam(){
        //tworzymy Team i zamieniamy DTO na zwykly Team
        return new Team.TeamBuilder()
                .setTeamName(teamName)
                .setCoachName(coachName)
                .setSponsorsName(sponsorsName)
                .setLeague(league)
                .setFoundingYear(foundingYear)
                .build();

    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getSponsorsName() {
        return sponsorsName;
    }

    public void setSponsorsName(String sponsorsName) {
        this.sponsorsName = sponsorsName;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }
}
