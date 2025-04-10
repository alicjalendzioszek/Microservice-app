package com.example.lab4player.dto;

import com.example.lab4player.enums.Positions;
import com.example.lab4player.models.Player;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class PlayerReadDTO implements Comparable<PlayerReadDTO>{
    String name;
    Positions position;
    int number;
    float height;
    String teamName;
    UUID id;

    public PlayerReadDTO(Player player){
        this.name = player.getName();
        this.position = player.getPosition();
        this.number = player.getNumber();
        this.height = player.getHeight();
        //this.teamName = "cos";
        this.teamName=player.getTeam().getTeamName();
        this.id = player.getId();
    }
    @Override
    public int compareTo(PlayerReadDTO playerDTO) {
        int nameCompare = this.getName().compareTo(playerDTO.getName());
        int positionCompare = this.getPosition().compareTo(playerDTO.getPosition());
        int numberCompare = Integer.compare(this.getNumber(),playerDTO.getNumber());
        int heightCompare = Float.compare(this.getHeight(),playerDTO.getHeight());

        if(nameCompare!=0)
        {
            return nameCompare;
        } else if(positionCompare!=0)
        {
            return positionCompare;
        }else if(numberCompare!=0)
        {
            return numberCompare;
        }else if(heightCompare!=0)
        {
            return heightCompare;
        }

        return 0;

    }
}
