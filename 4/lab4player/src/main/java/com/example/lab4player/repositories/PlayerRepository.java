package com.example.lab4player.repositories;

import com.example.lab4player.models.Player;
import com.example.lab4player.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    Optional<Player> findById(UUID id);  // Powinna to robić JPA
    List<Player> findByTeam(Team team);
    //Zamiast ręcznie implementować metody dostępu do bazy, repozytorium może dziedziczyć po interfejsie JpaRepository, co umożliwia korzystanie z gotowych, predefiniowanych metod do operacji CRUD (Create, Read, Update, Delete) bez potrzeby pisania kodu SQL.
}
