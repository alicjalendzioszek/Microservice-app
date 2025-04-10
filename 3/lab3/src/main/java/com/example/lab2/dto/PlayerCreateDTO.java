package com.example.lab2.dto;

import com.example.lab2.enums.Positions;
import com.example.lab2.models.Player;
import com.example.lab2.models.Team;

public class PlayerCreateDTO {

    String name;
    String position;
    int number;
    int height;

    //String teamName;

    // Default no-argument constructor
    public PlayerCreateDTO() {
    }
    public PlayerCreateDTO(String name, String position, int number, int height) {
        this.name = name;
        this.position = position;
        this.number = number;
        this.height = height;
        //this.teamName=teamName;
    }



    public Player convertPlayerDTOtoPlayer(Team team){
        //przyjmujemy team, w ktorym ma byc player
        //tworzymy playera i przypisujemy mu team i zamieniamy DTO na zwyklego playera
        return new Player.PlayerBuilder()
                .setName(name)
                .setNumber(number)
                .setHeight(height)
                .setPosition(Positions.valueOf(position))
                .setTeam(team)
                .build();

    }

//    public String getTeamName() {
//        return teamName;
//    }
//
//    public void setTeamName(String teamName) {
//        this.teamName = teamName;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
