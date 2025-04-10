package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Team zaksa = new Team.TeamBuilder()
                .setCoachName("Andrea Giani")
                .setFoundingYear(1994)
                .setTeamName("ZAKSA Kędzierzyn-Koźle")
                .setSponsorsName("Grupa Azoty ZAK")
                .setLeague("PlusLiga")
                .build();

        Player player1 = new Player.PlayerBuilder()
                .setName("Bartosz Kurek")
                .setNumber(1)
                .setHeight(205)
                .setPosition(Positions.ATAKUJĄCY)
                .setTeam(zaksa)
                .build();

        Player player2 = new Player.PlayerBuilder()
                .setName("Erik Shoji")
                .setNumber(22)
                .setHeight(184)
                .setPosition(Positions.LIBERO)
                .setTeam(zaksa)
                .build();

        Player player3 = new Player.PlayerBuilder()
                .setName("Marcin Janusz")
                .setNumber(5)
                .setHeight(191)
                .setPosition(Positions.ROZGRYWAJĄCY)
                .setTeam(zaksa)
                .build();

        Team trefl = new Team.TeamBuilder()
                .setCoachName("Mariusz Sordyl")
                .setFoundingYear(2005)
                .setTeamName("Trefl Gdańsk")
                .setSponsorsName("Trefl SA")
                .setLeague("PlusLiga")
                .build();

        Player player4 = new Player.PlayerBuilder()
                .setName("Lukas Kampa")
                .setNumber(11)
                .setHeight(195)
                .setPosition(Positions.ROZGRYWAJĄCY)
                .setTeam(trefl)
                .build();

        Player player5 = new Player.PlayerBuilder()
                .setName("Piotr Orczyk")
                .setNumber(17)
                .setHeight(190)
                .setPosition(Positions.PRZYJMUJACY)
                .setTeam(trefl)
                .build();

        List<Team> teams = new ArrayList<>();
        teams.add(zaksa);
        teams.add(trefl);

        teams.forEach(team -> {
            System.out.println(team);
            team.getPlayers().forEach(player -> {
                System.out.println("  - " + player);
            });
        });

        System.out.println("\n");

        var allPlayers = Stream.of(zaksa,trefl)//utworzenie strumienia zawierajacego 2 elementy
                .flatMap(team -> team.getPlayers().stream())
                .collect(Collectors.toSet());

        allPlayers.forEach(System.out::println);
        System.out.println("\n");

        allPlayers.stream()
                .filter(player -> player.getHeight() >= 195)
                .sorted(Comparator.comparing(Player::getHeight)) //rosnaca .reversed() bedzie malejaco
                .forEach(System.out::println);

        System.out.println("\n");

        //transformacja playerow na playerDTO
        List<PlayerDTO> playersDTO = allPlayers.stream()
                .map(player -> new PlayerDTO(player.getName(),player.getPosition(),player.getNumber(),player.getHeight(),player.getTeam().getTeamName()))
                .sorted()
                .collect(Collectors.toList());

        playersDTO.forEach(System.out::println);
        System.out.println("\n");

        String filePathSerialization = "serialization.txt";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePathSerialization))){
            oos.writeObject(teams);
            System.out.println("Ukończono serializację teamów.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Team> serializationTeam;

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePathSerialization))){
            serializationTeam = (List<Team>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Teamy po serializacji: \n");
        serializationTeam.forEach(team -> {
            System.out.println(team);
            team.getPlayers().forEach(player -> {
                System.out.println("  - " + player);
            });
        });


        ForkJoinPool threadPool = new ForkJoinPool(2);
        System.out.println("\n");
        
        try {
            threadPool.submit(() -> {
                teams.parallelStream()
                        .forEach(team -> {
                            System.out.println("Processing team: " + team);
                            team.getPlayers().forEach(player -> {
                                System.out.println(Thread.currentThread().getName() + " processing player: " + player);
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                        });
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
            try {
                if (!threadPool.awaitTermination(1, TimeUnit.SECONDS)) {
                    threadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                threadPool.shutdownNow();
            }
        }

        System.out.println("Processing complete.");
    }
}