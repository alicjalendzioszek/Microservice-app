package com.example.lab4team.services;

import com.example.lab4team.dto.TeamUpdateDTO;
import com.example.lab4team.models.Player;
import com.example.lab4team.models.Team;
import com.example.lab4team.repositories.TeamRepository;
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
    @Transactional
    public Optional<Team> updateTeam(UUID id, TeamUpdateDTO updatedTeam) {
        Optional<Team> existingTeamOpt = teamRepository.findById(id);

        if (existingTeamOpt.isPresent()) {
            Team existingTeam = existingTeamOpt.get();

            existingTeam.setTeamName(updatedTeam.getTeamName());
            existingTeam.setCoachName(updatedTeam.getCoachName());
            existingTeam.setSponsorsName(updatedTeam.getSponsorsName());
            existingTeam.setLeague(updatedTeam.getLeague());
            existingTeam.setFoundingYear(updatedTeam.getFoundingYear());

            teamRepository.save(existingTeam);
            return Optional.of(existingTeam);
        }
        return Optional.empty();
    }


}
