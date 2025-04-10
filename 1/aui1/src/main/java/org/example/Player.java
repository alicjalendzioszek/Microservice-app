package org.example;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Comparable<Player>, Serializable {
    String name;
    Positions position;
    int number;
    int height;
    Team team;

    public Player(PlayerBuilder playerBuilder) {
        this.name = playerBuilder.name;
        this.position = playerBuilder.position;
        this.number = playerBuilder.number;
        this.height = playerBuilder.height;
        this.team = playerBuilder.team;
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

    public float getHeight() {
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
        tekst="Player{name = " + this.name + ", position = " +this.position+", number = "+this.number+ ", height = "+this.height+", team = "+this.team+"}";
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
