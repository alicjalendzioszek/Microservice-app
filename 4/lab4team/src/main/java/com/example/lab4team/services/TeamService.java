package com.example.lab4team.services;

import com.example.lab4team.dto.TeamUpdateDTO;
import com.example.lab4team.models.Team;
import com.example.lab4team.repositories.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void saveTeam(Team team)
    {
        teamRepository.save(team);
    }
    @Transactional
    public Optional<Team> getTeamById(UUID id) {
        return teamRepository.findById(id);
    }

    @Transactional
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Transactional
    public void deleteTeam(UUID id) {
        Optional<Team> teamOpt = teamRepository.findById(id);
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
