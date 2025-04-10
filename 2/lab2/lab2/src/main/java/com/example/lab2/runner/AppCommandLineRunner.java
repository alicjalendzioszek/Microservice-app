package com.example.lab2.runner;

import com.example.lab2.enums.Positions;
import com.example.lab2.models.Player;
import com.example.lab2.models.Team;
import com.example.lab2.services.PlayerService;
import com.example.lab2.services.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    // service odpowiada za zarzadzanie repository
    // repository dziala bezposrednio na bazie danych
    TeamService teamService;
    PlayerService playerService;
    Scanner scanner = new Scanner(System.in); //do wpisywania użytkownika


    public AppCommandLineRunner(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean isRunning = true;

        while(isRunning) {
            int choice = chooseFromMenu();

            switch (choice) {
                case 1:
                    printAllTeams();
                    break;
                case 2:
                    printAllPlayers();
                    break;
                case 3:
                    addNewPlayer();
                    break;
                case 4:
                    deletePlayer();
                    break;
                case 5:
                    closeProgram();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wybrano niepoprawną opcje!");
            }
        }
    }

    private int chooseFromMenu()
    {
        int choice = 0;
        while(choice < 1 || choice > 5) {

            System.out.println("Wybierz komendę: ");
            System.out.println("1. wypisz wszystkie zespoły ");
            System.out.println("2. wypisz wszystkich zawodników ");
            System.out.println("3. dodaj nowego zawodnika ");
            System.out.println("4. usun zawodnika");
            System.out.println("5. zakończ ");


            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 1 || choice > 5) {
                    System.out.println("Niepoprawny wybór. Wybierz liczbę od 1 do 5.");
                }
            } else {
                System.out.println("Niepoprawne dane. Wprowadź liczbę całkowitą.");
                scanner.next();
            }

        }
        return choice;
    }

    private void printAllTeams()
    {
        List<Team> teams = teamService.getAllTeams();

        if(!teams.isEmpty())
        {
            System.out.println("Zespoły: "+teams.size());
            teams.forEach(System.out::println);
        }else {
            System.out.println("Nie ma żadnych zespołów!");
        }
    }

    private void printAllPlayers()
    {
        List<Player> players = playerService.getAllPlayers();

        if(!players.isEmpty())
        {
            System.out.println("Zawodnicy: ");
            players.forEach(System.out::println);
        }else {
            System.out.println("Nie ma żadnych zespołów!");
        }
    }

    private Positions choosePlayerPosition()
    {
        int choice = 0;
        while (choice < 1 || choice > 5) {
            System.out.println("Wybierz pozycję zawodnika:");
            System.out.println("1. ROZGRYWAJĄCY");
            System.out.println("2. PRZYJMUJĄCY");
            System.out.println("3. ATAKUJĄCY");
            System.out.println("4. ŚRODKOWY");
            System.out.println("5. LIBERO");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > 5) {
                    System.out.println("Niepoprawny wybór. Wybierz liczbę od 1 do 5.");
                }
            } else {
                System.out.println("Niepoprawne dane. Wprowadź liczbę całkowitą.");
                scanner.next();
            }
        }
        return switch (choice) {
            case 1 -> Positions.ROZGRYWAJĄCY;
            case 2 -> Positions.PRZYJMUJACY;
            case 3 -> Positions.ATAKUJĄCY;
            case 4 -> Positions.ŚRODKOWY;
            case 5 -> Positions.LIBERO;
            default -> null;
        };
    }

    private Optional<Team> chooseTeam()
    {
        Optional<Team> team = Optional.empty();

        while(team.isEmpty())
        {
            System.out.println("Wybierz team zawodnika, podając jego ID:");
            printAllTeams();
            String choiceID=scanner.nextLine();

            try {
                UUID teamId = UUID.fromString(choiceID);
                team = teamService.getTeamById(teamId);

                if (team.isEmpty()) {
                    System.out.println("Nie znaleziono zespołu o podanym ID. Spróbuj ponownie.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Nieprawidłowy format ID. Wprowadź poprawne UUID.");
            }
        }

        return team;
    }

    private void addNewTeam()
    {
        System.out.println("Podaj nazwę zespołu:");
        String teamName;

        while ((teamName = scanner.nextLine()).trim().isEmpty()) {
            System.out.println("Nazwa nie może być pusta. Spróbuj ponownie:");
        }
    }

    private void addNewPlayer()
    {
        System.out.println("Podaj imię i nazwisko zawodnika:");
        String playerName;
        while ((playerName = scanner.nextLine()).trim().isEmpty()) {
            System.out.println("Imię i nazwisko nie może być puste. Spróbuj ponownie:");
        }

        Positions playerPosition = choosePlayerPosition();

        int playerNumber = -1;
        while (playerNumber <= 0) {
            System.out.println("Podaj numer zawodnika:");

            // Odczytanie linii jako String
            String input = scanner.nextLine().trim();

            // Sprawdzanie, czy wejście nie jest puste
            if (input.isEmpty()) {
                System.out.println("Numer nie może być pusty. Wprowadź liczbę całkowitą.");
                continue;  // Przejdź do kolejnej iteracji pętli
            }

            try {
                playerNumber = Integer.parseInt(input);
                if (playerNumber <= 0) {
                    System.out.println("Numer musi być większy od 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy format numeru. Wprowadź liczbę całkowitą.");
            }
        }

        int playerHeight = -1;
        while (playerHeight <= 0) {
            System.out.println("Podaj wzrost zawodnika (w centymetrach):");

            // Odczytanie linii jako String
            String input = scanner.nextLine().trim();

            // Sprawdzanie, czy wejście nie jest puste
            if (input.isEmpty()) {
                System.out.println("Wzrost nie może być pusty. Wprowadź liczbę całkowitą.");
                continue;  // Przejdź do kolejnej iteracji pętli
            }

            try {
                playerHeight = Integer.parseInt(input);
                if (playerHeight <= 0) {
                    System.out.println("Wzrost musi być większy od 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy format wzrostu. Wprowadź liczbę całkowitą.");
            }
        }

        Optional<Team> playerTeamOptional = chooseTeam();
        Team playerTeam = playerTeamOptional.get();

        Player newPlayer = new Player.PlayerBuilder()
                .setName(playerName)
                .setPosition(playerPosition)
                .setHeight(playerHeight)
                .setNumber(playerNumber)
                .setTeam(playerTeam)
                .build();

        playerService.savePlayer(newPlayer);
        System.out.println("Dodano nowego zawodnika: " + playerName);
    }

    private void deletePlayer(){
        Optional<Player> player = Optional.empty();

        while(player.isEmpty())
        {
            System.out.println("Wybierz zawodnika, którego chcesz usunąć, podając jego ID:");
            printAllPlayers();
            String choiceID=scanner.nextLine();

            try {
                UUID playerId = UUID.fromString(choiceID);
                player = playerService.getPlayerById(playerId);

                if (player.isEmpty()) {
                    System.out.println("Nie znaleziono zawodnika o podanym ID. Spróbuj ponownie.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Nieprawidłowy format ID. Wprowadź poprawne UUID.");
            }
        }

        playerService.deletePlayer(player.get().getId());
        System.out.println("Usunięto zawodnika " + player.get().getName() + ".");
    }

    private void closeProgram()
    {
        System.out.println("Zamykanie programu...");
        scanner.close();
        System.exit(0);
    }

}
