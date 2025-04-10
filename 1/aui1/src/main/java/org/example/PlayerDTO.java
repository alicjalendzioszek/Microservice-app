package org.example;

import java.util.Objects;

public class PlayerDTO implements Comparable<PlayerDTO>{
    String name;
    Positions position;
    int number;
    float height;
    String teamName;

    public PlayerDTO(String name, Positions position, int number, float height, String teamName) {
        this.name = name;
        this.position = position;
        this.number = number;
        this.height = height;
        this.teamName = teamName;
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

    public void setHeight(float height) {
        this.height = height;
    }

    public String getTeam() {
        return teamName;
    }

    public void setTeam(Team team) {
        this.teamName = team.getTeamName();
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(name, position, number, height, teamName);
    }

    @Override
    public String toString()
    {
        String tekst;
        tekst="Player{name = " + this.name + ", position = " +this.position+", number = "+this.number+ ", height = "+this.height+", team = "+this.teamName+"}";
        return tekst;
    }

    @Override
    public int compareTo(PlayerDTO playerDTO) {
        int nameCompare = this.getName().compareTo(playerDTO.getName());
        int positionCompare = this.getPosition().compareTo(playerDTO.getPosition());
        int numberCompare = Integer.compare(this.getNumber(),playerDTO.getNumber());
        int heightCompare = Float.compare(this.getHeight(),playerDTO.getHeight());

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

