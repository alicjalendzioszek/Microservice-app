package com.example.lab2.services;

import com.example.lab2.models.Player;
import com.example.lab2.models.Team;
import com.example.lab2.repositories.PlayerRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    //dependency injection :D
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player savePlayer(Player player)
    {
        return playerRepository.save(player);
    }
    @Transactional
    public Optional<Player> getPlayerById(UUID id) {
        Optional<Player> playerOpt = playerRepository.findById(id);
        playerOpt.ifPresent(player -> Hibernate.initialize(player.getTeam()));  // Inicjalizacja leniwego pola `team`
        return playerOpt;
    }
    @Transactional
    public List<Player> getAllPlayers()
    {
        var players = playerRepository.findAll();
        players.forEach(player -> Hibernate.initialize(player.getTeam()));
        return players;
    }
    @Transactional
    public List<Player> getPlayersByTeam(Team team) {
        List<Player> players = playerRepository.findByTeam(team);
        players.forEach(player -> Hibernate.initialize(player.getTeam()));  // Inicjalizacja leniwego pola `team`
        return players;
    }
    public Optional<Player> updatePlayer(UUID playerId, Player updatedPlayer)
    {
        Optional<Player> existingPlayerOpt = playerRepository.findById(playerId);

        if(existingPlayerOpt.isPresent())
        {
            Player existingPlayer = existingPlayerOpt.get();

            existingPlayer.setName(updatedPlayer.getName());
            existingPlayer.setHeight(updatedPlayer.getHeight());
            existingPlayer.setNumber(updatedPlayer.getNumber());
            existingPlayer.setPosition(updatedPlayer.getPosition());
            existingPlayer.setTeam(updatedPlayer.getTeam());

            Player savedPlayer = playerRepository.save(existingPlayer);

            Hibernate.initialize(savedPlayer.getTeam());
            return Optional.of(savedPlayer);
        }
        return Optional.empty();
    }

    @Transactional
    public void deletePlayer(UUID id) {
        Optional<Player> playerOpt = playerRepository.findById(id);
        playerOpt.ifPresent(player -> Hibernate.initialize(player.getTeam()));  // Inicjalizacja leniwego pola `team`
        playerRepository.deleteById(id);
    }

}
