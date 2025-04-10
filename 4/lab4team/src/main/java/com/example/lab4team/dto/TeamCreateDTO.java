package com.example.lab4team.dto;

import com.example.lab4team.models.Team;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamCreateDTO {

    String teamName;
    String coachName;
    String sponsorsName;
    String league;
    int foundingYear;

    public Team convertTeamDTOtoTeam(){
        //tworzymy Team i zamieniamy DTO na zwykly Team
        return Team.builder()
                .id(UUID.randomUUID())
                .teamName(teamName)
                .coachName(coachName)
                .sponsorsName(sponsorsName)
                .league(league)
                .foundingYear(foundingYear)
                .build();
    }
}
