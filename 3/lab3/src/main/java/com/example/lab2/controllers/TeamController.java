package com.example.lab2.controllers;
import com.example.lab2.dto.TeamColletionDTO;
import com.example.lab2.dto.TeamCreateDTO;
import com.example.lab2.dto.TeamReadDTO;
import com.example.lab2.dto.TeamUpdateDTO;
import com.example.lab2.models.Player;
import com.example.lab2.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
public class TeamController {
    TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping
    public ResponseEntity<List<TeamColletionDTO>> getAllTeams() {
        var teams = teamService.getAllTeams();

        if (teams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var teamReadDTO = teams.stream()
                .map(team -> new TeamColletionDTO(team.getId(),team.getTeamName()))
                .toList();

        return new ResponseEntity<>(teamReadDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamReadDTO> getTeamById(@PathVariable UUID id) {
        var teamOpt = teamService.getTeamById(id);

        if (teamOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var team = teamOpt.get();

        Set<String> playerNames = team.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        var teamReadDTO = new TeamReadDTO(team.getTeamName(),team.getCoachName(),team.getSponsorsName(),team.getLeague(),team.getFoundingYear(),playerNames);

        return new ResponseEntity<>(teamReadDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamReadDTO> createTeam(@RequestBody TeamCreateDTO teamCreateDTO) {
        var team = teamCreateDTO.convertTeamDTOtoTeam();

        teamService.saveTeam(team);

        Set<String> playerNames = team.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        var teamReadDTO = new TeamReadDTO(team.getTeamName(),team.getCoachName(),team.getSponsorsName(),team.getLeague(),team.getFoundingYear(),playerNames);

        return new ResponseEntity<>(teamReadDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamReadDTO> updateTeam(@PathVariable UUID id, @RequestBody TeamUpdateDTO teamUpdateDTO) {
        var teamOpt = teamService.getTeamById(id);

        if (teamOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var team = teamOpt.get();
        teamService.updateTeam(team.getId(),teamUpdateDTO);

        Set<String> playerNames = team.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        var teamReadDTO = new TeamReadDTO(team.getTeamName(),team.getCoachName(),team.getSponsorsName(),team.getLeague(),team.getFoundingYear(),playerNames);

        return new ResponseEntity<>(teamReadDTO, HttpStatus.OK);
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


    @GetMapping("/{id}/players")
    public ResponseEntity<Set<String>> getTeamPlayers(@PathVariable UUID id) {
        var teamOpt = teamService.getTeamById(id);

        if (teamOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var team = teamOpt.get();
        var players = team.getPlayers();

        if(players.isEmpty()) {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}

        Set<String> playerNames = team.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        return new ResponseEntity<>(playerNames, HttpStatus.OK);
    }



}
