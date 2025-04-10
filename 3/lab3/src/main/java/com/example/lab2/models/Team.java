package com.example.lab2.models;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;
import static jakarta.persistence.FetchType.*;

@Entity
@Table(name="teams")
public class Team implements Comparable<Team>, Serializable {


    @Id
    @Column(name="id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name="team_name", nullable = false)
    String teamName;
    @Column(name="coach_name", nullable = false)
    String coachName;
    @Column(name="sponsors_name", nullable = false)
    String sponsorsName;
    @Column(name="league", nullable = false)
    String league;
    @Column(name="founding", nullable = false)
    int foundingYear;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch= LAZY)
    //fetch=lazy - Fetching category should not fetch elements by default.
    Set<Player> players;
    public Team(){}

    public Team(TeamBuilder teamBuilder) {
        this.players = new TreeSet<>();
        this.teamName = teamBuilder.teamName;
        this.coachName = teamBuilder.coachName;
        this.sponsorsName = teamBuilder.sponsorsName;
        this.league = teamBuilder.league;
        this.foundingYear = teamBuilder.foundingYear;
        this.id=UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getSponsorsName() {
        return sponsorsName;
    }

    public void setSponsorsName(String sponsorsName) {
        this.sponsorsName = sponsorsName;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        player.setTeam(this);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(teamName, coachName, sponsorsName, league, foundingYear);
    }
    @Override
    public String toString()
    {
        String tekst;
        tekst="UUID = "+
                this.id+
                " Team{name = " +
                this.teamName +
                ", coach name = " +
                this.coachName+
                ", sponsor's name = "+
                this.sponsorsName+
                ", league = "+
                this.league+
                ", founding year = "+
                this.foundingYear;

        tekst += "}";

        return tekst;
    }

    @Override
    public int compareTo(Team team) {
        int teamNameCompare=this.getTeamName().compareTo(team.getTeamName());
        int coachNameCompare=this.getCoachName().compareTo(team.getCoachName());
        int sponsorsNameCompare=this.getSponsorsName().compareTo(team.getSponsorsName());
        int leagueCompare=this.getLeague().compareTo(team.getLeague());
        int foundingYearCompare=Integer.compare(this.getFoundingYear(),team.getFoundingYear());

        if(teamNameCompare!=0)
        {
            return teamNameCompare;
        } else if(coachNameCompare!=0)
        {
            return coachNameCompare;
        }else if(sponsorsNameCompare!=0)
        {
            return sponsorsNameCompare;
        }else if(leagueCompare!=0)
        {
            return leagueCompare;
        }else if(foundingYearCompare!=0)
        {
            return foundingYearCompare;
        }

        return 0;
    }

    public static class TeamBuilder{
        String teamName;
        String coachName;
        String sponsorsName;
        String league;
        int foundingYear;
        Set<Player> players;

        public TeamBuilder(){
            players = new TreeSet<>();
        }

        public TeamBuilder setTeamName(String teamName){
            this.teamName=teamName;
            return this;
        }
        public TeamBuilder setCoachName(String coachName){
            this.coachName=coachName;
            return this;
        }
        public TeamBuilder setSponsorsName(String sponsorsName){
            this.sponsorsName=sponsorsName;
            return this;
        }
        public TeamBuilder setLeague(String league){
            this.league=league;
            return this;
        }
        public TeamBuilder setFoundingYear(Integer foundingYear){
            this.foundingYear=foundingYear;
            return this;
        }
        public TeamBuilder addPlayer(Player player){
            this.players.add(player);
            return this;
        }
        public Team build(){
            return new Team(this);
        }
    }
}
