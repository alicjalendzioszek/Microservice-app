package com.example.lab4player.controllers;

import com.example.lab4player.dto.PlayerReadDTO;
import com.example.lab4player.models.Team;
import com.example.lab4player.services.PlayerService;
import com.example.lab4player.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/players/teams")
public class TeamController {
    TeamService teamService;
    PlayerService playerService;

    @Autowired
    public TeamController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Void> createTeam(@RequestBody Team team) {
        try {
            System.out.println("Otrzymano żądanie POST do /players/teams z payloadem: " + team);
            teamService.saveTeam(team);
            System.out.println("A new team has been saved.");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/players")
    public ResponseEntity<List<PlayerReadDTO>> getTeamPlayers(@PathVariable UUID id) {
        var teamOpt = teamService.getTeamById(id);

        if (teamOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var team = teamOpt.get();
        var players = playerService.getPlayersByTeam(team);

        if(players.isEmpty()) {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}

        List<PlayerReadDTO> playersFromTeam = players.stream()
                .map(player -> new PlayerReadDTO(
                        player.getName(),
                        player.getPosition(),
                        player.getNumber(),
                        player.getHeight(),
                        //"cos",
                        player.getTeam().getTeamName(),
                        player.getId()
                ))
                .toList();

        return new ResponseEntity<>(playersFromTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable UUID id) {
        var teamOpt = teamService.getTeamById(id);

        if (teamOpt.isEmpty()) {
            return new ResponseEntity<>("Nie udało się znaleźć zespołu.",HttpStatus.NOT_FOUND);
        }

        teamService.deleteTeam(id);

        return new ResponseEntity<>("Usunięto zespół.",HttpStatus.NO_CONTENT);
    }
}