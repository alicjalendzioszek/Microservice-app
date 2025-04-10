package com.example.lab2.controllers;
import com.example.lab2.dto.PlayerCollectionDTO;
import com.example.lab2.dto.PlayerCreateDTO;
import com.example.lab2.dto.PlayerReadDTO;
import com.example.lab2.dto.PlayerUpdateDTO;
import com.example.lab2.services.PlayerService;
import com.example.lab2.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayerController {
    PlayerService playerService;
    TeamService teamService;

    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerCollectionDTO>> getAllPlayers() {
        var players = playerService.getAllPlayers();

        var dtoPlayersCollection = players.stream()
                .map(player -> new PlayerCollectionDTO(player.getId(), player.getName()))
                .toList();

        if (dtoPlayersCollection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(dtoPlayersCollection, HttpStatus.OK );
    }

    @GetMapping("/{playerID}")
    public ResponseEntity<PlayerReadDTO> getPlayerById(@PathVariable UUID playerID) {
        var playerOpt = playerService.getPlayerById(playerID);

        if (playerOpt.isPresent()) {
            var player = playerOpt.get();

            var playerReadDTO = new PlayerReadDTO(
                    player.getName(),
                    player.getPosition(),
                    player.getNumber(),
                    player.getHeight(),
                    player.getTeam().getTeamName()
            );

            return new ResponseEntity<>(playerReadDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teams/{teamID}")
    public ResponseEntity<PlayerReadDTO> createPlayer(@PathVariable UUID teamID, @RequestBody PlayerCreateDTO playerCreateDTO) {
        var teamOpt = teamService.getTeamById(teamID);

        if (teamOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            var team = teamOpt.get();
            var savedPlayer = playerService.savePlayer(playerCreateDTO.convertPlayerDTOtoPlayer(team));

            return new ResponseEntity<>(new PlayerReadDTO(savedPlayer), HttpStatus.CREATED);
        }
    }

    @PutMapping("{playerID}")
    public ResponseEntity<PlayerReadDTO> updatePlayer( @PathVariable UUID playerID, @RequestBody PlayerUpdateDTO playerUpdateDTO) {
        var playerOpt = playerService.getPlayerById(playerID);

        if (playerOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            var player = playerOpt.get();
            var updatedPlayer = playerService.updatePlayer(player.getId(), playerUpdateDTO);

            return updatedPlayer.map(value -> new ResponseEntity<>(new PlayerReadDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
    }

    @DeleteMapping("{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable UUID playerId) {
        var playerOpt = playerService.getPlayerById(playerId);

        if (playerOpt.isEmpty()) {
            return new ResponseEntity<>("Nie udało się znaleźć gracza.",HttpStatus.NOT_FOUND);
        }

        playerService.deletePlayer(playerId);
        return new ResponseEntity<>("Usunięto gracza.",HttpStatus.NO_CONTENT);
    }
}
