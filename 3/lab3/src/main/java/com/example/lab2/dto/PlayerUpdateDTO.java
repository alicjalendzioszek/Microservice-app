package com.example.lab2.dto;

import com.example.lab2.enums.Positions;
import com.example.lab2.models.Player;
import com.example.lab2.models.Team;

import java.util.UUID;

public class PlayerUpdateDTO {
    String name;
    Positions position;
    int number;
    int height;


    public PlayerUpdateDTO(String name, Positions position, int number, int height) {
        this.name = name;
        this.position = position;
        this.number = number;
        this.height = height;
    }

    public Player updatePlayer(Team team){
        //przyjmujemy team, w ktorym ma byc player
        //tworzymy playera i przypisujemy mu team i zamieniamy DTO na zwyklego playera
        return new Player.PlayerBuilder()
                .setName(name)
                .setNumber(number)
                .setHeight(height)
                .setPosition(position)
                .setTeam(team)
                .build();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
