package com.example.lab2.dto;

import java.util.UUID;

public class TeamColletionDTO {

    UUID id;
    String teamName;

    public TeamColletionDTO(UUID id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
