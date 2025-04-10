package com.example.lab4team.dto;
import lombok.*;

import java.util.UUID;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class TeamCollectionDTO {

    UUID id;
    String teamName;

}
