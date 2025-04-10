package com.example.lab2.models;
import com.example.lab2.enums.Positions;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name="players")
public class Player implements Comparable<Player>, Serializable {
    @Id
    @Column(name="id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name="player_name", nullable = false)
    String name;
    @Enumerated(EnumType.STRING)
    @Column(name="position", nullable = false)
    Positions position;
    @Column(name="number", nullable = false)
    int number;
    @Column(name="height", nullable = false)
    int height;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="team_id", referencedColumnName = "id")
    Team team;
    public Player() {}
    public Player(PlayerBuilder playerBuilder) {
        this.name = playerBuilder.name;
        this.position = playerBuilder.position;
        this.number = playerBuilder.number;
        this.height = playerBuilder.height;
        this.team = playerBuilder.team;
        this.id=UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(name, position, number, height, team);
    }

    @Override
    public String toString()
    {
        String tekst;
        tekst="UUID = "+
                this.id+
                " Player{name = " +
                this.name +
                ", position = " +
                this.position+
                ", number = "+
                this.number+
                ", height = "+
                this.height+
                ", team = "+
                this.team.getTeamName()+
                "}";
        return tekst;
    }

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

    public static class PlayerBuilder {
        String name;
        Positions position;
        int number;
        int height;
        Team team;

        public PlayerBuilder setName(String name){
            this.name=name;
            return this;
        }
        public PlayerBuilder setPosition(Positions position){
            this.position=position;
            return this;
        }
        public PlayerBuilder setNumber(Integer number){
            this.number=number;
            return this;
        }
        public PlayerBuilder setHeight(Integer height){
            this.height=height;
            return this;
        }
        public PlayerBuilder setTeam(Team team){
            this.team=team;
            return this;
        }

        public Player build(){
            Player player = new Player(this);
            if(this.team!=null){
                this.team.addPlayer(player);
            }
            return player;
        }

    }

}
