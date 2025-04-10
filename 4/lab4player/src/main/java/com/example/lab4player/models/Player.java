package com.example.lab4player.models;
import com.example.lab4player.enums.Positions;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name="players")
public class Player implements Comparable<Player>, Serializable {
    @Id
    @Column(name="id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name="player_name", nullable = false)
    String name;

    @Enumerated(EnumType.STRING)
    @Column(name="position", nullable = false, columnDefinition = "ENUM('ATAKUJACY', 'LIBERO', 'PRZYJMUJACY', 'ROZGRYWAJACY', 'SRODKOWY')")
    Positions position;

    @Column(name="number", nullable = false)
    int number;

    @Column(name="height", nullable = false)
    int height;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="team_id", referencedColumnName = "id")
    Team team;

    @Override
    public int compareTo(Player player) {
        int nameCompare = this.getName().compareTo(player.getName());
        int positionCompare = this.getPosition().compareTo(player.getPosition());
        int numberCompare = Integer.compare(this.getNumber(),player.getNumber());
        int heightCompare = Float.compare(this.getHeight(),player.getHeight());

        if(nameCompare!=0)
        {
            return nameCompare;
        } else if(positionCompare!=0)
        {
            return positionCompare;
        }else if(numberCompare!=0)
        {
            return numberCompare;
        }else if(heightCompare!=0)
        {
            return heightCompare;
        }

        return 0;
    }

}
