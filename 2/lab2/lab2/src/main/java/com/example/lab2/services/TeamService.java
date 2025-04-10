package com.example.lab2.services;

import com.example.lab2.models.Player;
import com.example.lab2.models.Team;
import com.example.lab2.repositories.TeamRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team saveTeam(Team team)
    {
        return teamRepository.save(team);
    }
    @Transactional
    public Optional<Team> getTeamById(UUID id) {
        Optional<Team> teamOpt = teamRepository.findById(id);
        teamOpt.ifPresent(team -> Hibernate.initialize(team.getPlayers()));  // Inicjalizacja leniwego pola `players`
        return teamOpt;
    }

    @Transactional
    public List<Team> getAllTeams() {
        var teams = teamRepository.findAll();
        teams.forEach(team -> Hibernate.initialize(team.getPlayers()));  // Inicjalizacja leniwego pola `players`
        return teams;
    }

    @Transactional
    public void deleteTeam(UUID id) {
        Optional<Team> teamOpt = teamRepository.findById(id);
        teamOpt.ifPresent(team -> Hibernate.initialize(team.getPlayers()));  // Inicjalizacja leniwego pola `players`
        teamRepository.deleteById(id);
    }
}
