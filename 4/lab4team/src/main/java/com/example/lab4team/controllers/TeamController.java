package com.example.lab4team.controllers;

import com.example.lab4team.dto.TeamCollectionDTO;
import com.example.lab4team.dto.TeamCreateDTO;
import com.example.lab4team.dto.TeamReadDTO;
import com.example.lab4team.dto.TeamUpdateDTO;
import com.example.lab4team.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/teams")
public class TeamController {

    TeamService teamService;
    RestTemplate restTemplate;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
        this.restTemplate = new RestTemplate();
    }


    @GetMapping
    public ResponseEntity<List<TeamCollectionDTO>> getAllTeams() {
        System.out.println("Otrzymano żądanie GET /teams");
        var teams = teamService.getAllTeams();

        if (teams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var teamReadDTO = teams.stream()
                .map(team -> new TeamCollectionDTO(team.getId(),team.getTeamName()))
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


        var teamReadDTO = new TeamReadDTO(team.getTeamName(),team.getCoachName(),team.getSponsorsName(),team.getLeague(),team.getFoundingYear());

        return new ResponseEntity<>(teamReadDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamReadDTO> createTeam(@RequestBody TeamCreateDTO teamCreateDTO) {
        var team = teamCreateDTO.convertTeamDTOtoTeam();

        teamService.saveTeam(team);
        restTemplate.postForEntity("http://player:8081/players/teams",team,Void.class);

        var teamReadDTO = new TeamReadDTO(team.getTeamName(),team.getCoachName(),team.getSponsorsName(),team.getLeague(),team.getFoundingYear());

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


        var teamReadDTO = new TeamReadDTO(team.getTeamName(),team.getCoachName(),team.getSponsorsName(),team.getLeague(),team.getFoundingYear());

        return new ResponseEntity<>(teamReadDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable UUID id) {
        var teamOpt = teamService.getTeamById(id);

        if (teamOpt.isEmpty()) {
            return new ResponseEntity<>("Nie udało się znaleźć zespołu.",HttpStatus.NOT_FOUND);
        }

        teamService.deleteTeam(id);
        restTemplate.delete("http://player:8081/players/teams/{id}",id,Void.class);

        return new ResponseEntity<>("Usunięto zespół.",HttpStatus.NO_CONTENT);
    }

}
