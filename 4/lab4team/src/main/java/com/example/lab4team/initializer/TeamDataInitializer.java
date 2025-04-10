package com.example.lab4team.initializer;

import com.example.lab4team.appConfig.AppConfiguration;
import com.example.lab4team.dto.TeamReadDTO;
import com.example.lab4team.models.Team;
import com.example.lab4team.services.TeamService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class TeamDataInitializer {

    TeamService teamService;
    RestTemplate restTemplate;

    @Autowired
    public TeamDataInitializer(TeamService teamService, RestTemplate restTemplate) {
        this.teamService = teamService;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void initialize() {
        Team zaksa = Team.builder()
                .id(UUID.randomUUID())
                .coachName("Andrea Giani")
                .foundingYear(1994)
                .teamName("ZAKSA Kędzierzyn-Koźle")
                .sponsorsName("Grupa Azoty ZAK")
                .league("PlusLiga")
                .build();

        Team trefl = Team.builder()
                .id(UUID.randomUUID())
                .coachName("Mariusz Sordyl")
                .foundingYear(2005)
                .teamName("Trefl Gdańsk")
                .sponsorsName("Trefl SA")
                .league("PlusLiga")
                .build();

        teamService.saveTeam(zaksa);
        teamService.saveTeam(trefl);
        try{
            restTemplate.postForEntity("http://player:8081/players/teams",zaksa,Void.class);
            restTemplate.postForEntity("http://player:8081/players/teams",trefl,Void.class);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
