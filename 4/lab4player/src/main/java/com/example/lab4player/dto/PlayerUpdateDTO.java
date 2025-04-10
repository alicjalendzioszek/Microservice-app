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
public class PlayerUpdateDTO {

    String name;
    Positions position;
    int number;
    int height;


    public Player updatePlayer(Team team){
        //przyjmujemy team, w ktorym ma byc player
        //tworzymy playera i przypisujemy mu team i zamieniamy DTO na zwyklego playera
        return Player.builder()
                .id(UUID.randomUUID())
                .name(name)
                .number(number)
                .height(height)
                .position(position)
                .team(team)
                .build();

    }
}
