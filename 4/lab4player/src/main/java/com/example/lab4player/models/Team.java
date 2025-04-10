package com.example.lab4player.models;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;
import static jakarta.persistence.FetchType.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode

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

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch= EAGER)
    //fetch=lazy - Fetching category should not fetch elements by default.
    Set<Player> players;

    public void addPlayer(Player player) {
        this.players.add(player);
        player.setTeam(this);
    }

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
