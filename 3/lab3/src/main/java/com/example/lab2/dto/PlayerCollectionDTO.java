package com.example.lab2.dto;

import java.util.UUID;

public class PlayerCollectionDTO {

    UUID playerID;
    String name;

    public PlayerCollectionDTO(UUID playerID, String name) {
        this.playerID = playerID;
        this.name = name;
    }

    public UUID getPlayerID() {
        return playerID;
    }

    public void setPlayerID(UUID playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
