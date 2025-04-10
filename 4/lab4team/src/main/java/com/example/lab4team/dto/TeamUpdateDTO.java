package com.example.lab4team.dto;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class TeamUpdateDTO {

    String teamName;
    String coachName;
    String sponsorsName;
    String league;
    int foundingYear;

}
