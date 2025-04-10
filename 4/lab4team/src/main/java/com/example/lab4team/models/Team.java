package com.example.lab4team.models;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name="teams")
public class Team implements Comparable<Team>, Serializable {

    @Id
    @Column(name="id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name="team_name", nullable = false)
    String teamName;

    @Column(name="coach_name", nullable = false)
    String coachName;

    @Column(name="sponsors_name", nullable = false)
    String sponsorsName;

    @Column(name="league", nullable = false)
    String league;

    @Column(name="founding", nullable = false)
    int foundingYear;

    @Override
    public int compareTo(Team team) {
        int teamNameCompare=this.getTeamName().compareTo(team.getTeamName());
        int coachNameCompare=this.getCoachName().compareTo(team.getCoachName());
        int sponsorsNameCompare=this.getSponsorsName().compareTo(team.getSponsorsName());
        int leagueCompare=this.getLeague().compareTo(team.getLeague());
        int foundingYearCompare=Integer.compare(this.getFoundingYear(),team.getFoundingYear());

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
