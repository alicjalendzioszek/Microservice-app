package com.example.lab4team.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class TeamReadDTO implements Comparable<TeamReadDTO> {
    String teamName;
    String coachName;
    String sponsorsName;
    String league;
    int foundingYear;

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
