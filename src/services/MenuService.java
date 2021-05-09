package services;

import model.League;

import java.util.Scanner;

public class MenuService {
    public static void createMenu() {
        Scanner scanner = new Scanner(System.in);

        String input;
        byte exitNumber = 5;
        while (true) {
            System.out.println("1. Info about my team");
            System.out.println("2. Show standings");
            System.out.println("3. Complete all matches step by step");
            System.out.println("4. Play the entire tournament at once");
            if (League.isNullify()) {
                exitNumber = 6;
                System.out.println("5. Start tournament again");
            } else {
                exitNumber = 5;
            }
            System.out.printf("%d. Exit\n", exitNumber);

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
                    if (League.isNullify()) {
                        LeagueService.play();
                    } else {
                        System.out.println("Bye. Come back again to FootballManager!");
                        System.exit(0);
                    }
                    break;
                case "6":
                    if (League.isNullify()) {
                        System.out.println("Bye. Come back again to FootballManager!");
                        System.exit(0);
                    }
                    break;
                default:
            }
        }
    }
}
