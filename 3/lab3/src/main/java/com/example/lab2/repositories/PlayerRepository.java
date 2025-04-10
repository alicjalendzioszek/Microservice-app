package com.example.lab2.repositories;

import com.example.lab2.models.Player;
import com.example.lab2.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {

    List<Player> findByTeam(Team team);
    //Zamiast ręcznie implementować metody dostępu do bazy, repozytorium może dziedziczyć po interfejsie JpaRepository, co umożliwia korzystanie z gotowych, predefiniowanych metod do operacji CRUD (Create, Read, Update, Delete) bez potrzeby pisania kodu SQL.
}
