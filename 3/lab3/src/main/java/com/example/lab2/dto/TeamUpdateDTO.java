package com.example.lab2.dto;

import java.util.Set;

public class TeamUpdateDTO {

    String teamName;
    String coachName;
    String sponsorsName;
    String league;
    int foundingYear;


    public TeamUpdateDTO(String teamName, String coachName, String sponsorsName, String league, int foundingYear) {
        this.teamName = teamName;
        this.coachName = coachName;
        this.sponsorsName = sponsorsName;
        this.league = league;
        this.foundingYear = foundingYear;
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
