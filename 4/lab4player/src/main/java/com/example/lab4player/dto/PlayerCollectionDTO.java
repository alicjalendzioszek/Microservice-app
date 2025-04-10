package com.example.lab4player.dto;

import com.example.lab4player.enums.Positions;
import com.example.lab4player.models.Player;
import com.example.lab4player.models.Team;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class PlayerCollectionDTO {

    UUID playerID;
    String name;

}
