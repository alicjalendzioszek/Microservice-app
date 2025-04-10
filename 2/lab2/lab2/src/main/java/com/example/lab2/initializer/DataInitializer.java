package com.example.lab2.initializer;

import com.example.lab2.enums.Positions;
import com.example.lab2.models.Player;
import com.example.lab2.models.Team;
import com.example.lab2.services.PlayerService;
import com.example.lab2.services.TeamService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    PlayerService playerService;
    TeamService teamService;

    @Autowired
    public DataInitializer(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @PostConstruct
    public void initialize() {
        Team zaksa = new Team.TeamBuilder()
                .setCoachName("Andrea Giani")
                .setFoundingYear(1994)
                .setTeamName("ZAKSA Kędzierzyn-Koźle")
                .setSponsorsName("Grupa Azoty ZAK")
                .setLeague("PlusLiga")
                .build();

        Player player1 = new Player.PlayerBuilder()
                .setName("Bartosz Kurek")
                .setNumber(1)
                .setHeight(205)
                .setPosition(Positions.ATAKUJĄCY)
                .setTeam(zaksa)
                .build();

        Player player2 = new Player.PlayerBuilder()
                .setName("Erik Shoji")
                .setNumber(22)
                .setHeight(184)
                .setPosition(Positions.LIBERO)
                .setTeam(zaksa)
                .build();

        Player player3 = new Player.PlayerBuilder()
                .setName("Marcin Janusz")
                .setNumber(5)
                .setHeight(191)
                .setPosition(Positions.ROZGRYWAJĄCY)
                .setTeam(zaksa)
                .build();

        Team trefl = new Team.TeamBuilder()
                .setCoachName("Mariusz Sordyl")
                .setFoundingYear(2005)
                .setTeamName("Trefl Gdańsk")
                .setSponsorsName("Trefl SA")
                .setLeague("PlusLiga")
                .build();

        Player player4 = new Player.PlayerBuilder()
                .setName("Lukas Kampa")
                .setNumber(11)
                .setHeight(195)
                .setPosition(Positions.ROZGRYWAJĄCY)
                .setTeam(trefl)
                .build();

        Player player5 = new Player.PlayerBuilder()
                .setName("Piotr Orczyk")
                .setNumber(17)
                .setHeight(190)
                .setPosition(Positions.PRZYJMUJACY)
                .setTeam(trefl)
                .build();

        teamService.saveTeam(zaksa);
        teamService.saveTeam(trefl);

        playerService.savePlayer(player1);
        playerService.savePlayer(player2);
        playerService.savePlayer(player3);
        playerService.savePlayer(player4);
        playerService.savePlayer(player5);
    }
}
