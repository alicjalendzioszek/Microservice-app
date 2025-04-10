package com.example.lab2.dto;

import com.example.lab2.models.Player;

import java.util.Objects;
import java.util.Set;

public class TeamReadDTO implements Comparable<TeamReadDTO>{
    String teamName;
    String coachName;
    String sponsorsName;
    String league;
    int foundingYear;
    Set<String> playersNames;

    public TeamReadDTO(String teamName, String coachName, String sponsorsName, String league, int foundingYear, Set<String> playersNames) {
        this.teamName = teamName;
        this.coachName = coachName;
        this.sponsorsName = sponsorsName;
        this.league = league;
        this.foundingYear = foundingYear;
        this.playersNames = playersNames;
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

    public Set<String> getPlayers() {
        return playersNames;
    }

    public void addPlayersName(Player player) {
        this.playersNames.add(player.getName());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(teamName, coachName, sponsorsName, league, foundingYear, playersNames);
    }
    @Override
    public String toString()
    {
        String tekst;
        tekst="Team{name = " + this.teamName + ", coach name = " +this.coachName+", sponsor's name = "+this.sponsorsName+ ", league = "+this.league+", founding year = "+this.foundingYear+", players = [";

        for(String player:playersNames){
            tekst+=player + ", ";
        }

        if (!playersNames.isEmpty()) {
            tekst = tekst.substring(0, tekst.length() - 2);
        }

        tekst += "]}";

        return tekst;
    }

    @Override
    public int compareTo(TeamReadDTO TeamDTO) {
        int teamNameCompare=this.getTeamName().compareTo(TeamDTO.getTeamName());
        int coachNameCompare=this.getCoachName().compareTo(TeamDTO.getCoachName());
        int sponsorsNameCompare=this.getSponsorsName().compareTo(TeamDTO.getSponsorsName());
        int leagueCompare=this.getLeague().compareTo(TeamDTO.getLeague());
        int foundingYearCompare=Integer.compare(this.getFoundingYear(),TeamDTO.getFoundingYear());

        if(teamNameCompare!=0)
        {
            return teamNameCompare;
        } else if(coachNameCompare!=0)
        {
            return coachNameCompare;
        }else if(sponsorsNameCompare!=0)
        {
            return sponsorsNameCompare;
        }else if(leagueCompare!=0)
        {
            return leagueCompare;
        }else if(foundingYearCompare!=0)
        {
            return foundingYearCompare;
        }

        return 0;
    }
}

