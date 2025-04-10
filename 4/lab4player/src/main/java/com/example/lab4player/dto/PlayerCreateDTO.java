package com.example.lab4player.dto;

import com.example.lab4player.enums.Positions;
import com.example.lab4player.models.Player;
import com.example.lab4player.models.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class PlayerCreateDTO {
    String name;
    String position;
    int number;
    int height;

    public Player convertPlayerDTOtoPlayer(Team team){
        //przyjmujemy team, w ktorym ma byc player
        //tworzymy playera i przypisujemy mu team i zamieniamy DTO na zwyklego playera
        return Player.builder()
                .id(UUID.randomUUID())
                .name(name)
                .number(number)
                .height(height)
                .position(Positions.valueOf(position))
                .team(team)
                .build();
    }

}
