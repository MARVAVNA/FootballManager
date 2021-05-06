package services;

import java.util.Scanner;

public class MenuService {
    public static void createMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean check = true;

        String input;
        while (check) {
            System.out.println("1. Info about my team");
            System.out.println("2. Show standings");
            System.out.println("3. Complete all matches step by step");
            System.out.println("4. Play the entire tournament at once");
            System.out.println("5. Exit");

            input = scanner.next();

            switch (input) {
                case "1":
                    TeamService.getMyTeam().printInfo();
                    break;
                case "2":
                    LeagueService.getStandings();
                    break;
                case "3":
                    GameService.stepByStep();
                    break;
                case "4":
                    GameService.allGames();
                    break;
                case "5":
                    System.out.println("Bye. Come back again to FootballManager!");
                    check = false;
                    break;
                default:
            }
        }
    }
}
