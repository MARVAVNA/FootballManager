package services;

import model.Team;

public class GameService {
    private static byte tourCount;

    public static void stepByStep() {
        otherGames();
    }

    private static byte game() {
        return (byte) (Math.random() * 3);
    }

    private static void otherGames() {
        if (tourCount != 38) {
            byte gameResult;
            Team[] teams = shuffleTeams(LeagueService.getMyLeague().getTeams());
            for (int i = 0; i < 10; i++) {
                gameResult = game();
                if (teams[i].getName().equals(TeamService.getMyTeam().getName()) || teams[i + 10].getName().equals(TeamService.getMyTeam().getName())) {
                    switch (gameResult) {
                        case 0:
                            TeamService.getMyTeam().setPoints((short) (TeamService.getMyTeam().getPoints() + 3));
                            System.out.println("You lose");
                            break;
                        case 1:
                            TeamService.getMyTeam().setPoints((short) (TeamService.getMyTeam().getPoints() + 1));
                            System.out.println("Draw");
                            break;
                        case 2:
                            System.out.println("You win");
                            break;
                    }
                }
                switch (gameResult) {
                    case 0:
                        teams[i].setPoints((short) (teams[i].getPoints() + 3));
                        break;
                    case 1:
                        teams[i].setPoints((short) (teams[i].getPoints() + 1));
                        teams[i + 10].setPoints((short) (teams[i].getPoints() + 1));
                        break;
                    case 2:
                        teams[i + 10].setPoints((short) (teams[i].getPoints() + 3));
                        break;
                }
            }
            tourCount++;
            if (tourCount == 38) {
                LeagueService.sortStandings();
                results(LeagueService.getMyLeague().getTeams());
            }
        } else {
            results(LeagueService.getMyLeague().getTeams());
        }
    }

    private static Team[] shuffleTeams(Team[] teams) {
        for (int i = 0; i < teams.length; i++) {
            byte r = (byte) (Math.random() * teams.length);
            byte r2 = (byte) (Math.random() * teams.length);
            Team temp = teams[r];
            teams[r] = teams[r2];
            teams[r2] = temp;
        }

        return teams;
    }

    public static void allGames() {
        if (tourCount != 38) {
            byte count = (byte) (38 - tourCount);
            for (int i = 0; i < count; i++) {
                otherGames();
            }

            if (tourCount == 38) {
                LeagueService.sortStandings();
                results(LeagueService.getMyLeague().getTeams());
            }
        } else {
            results(LeagueService.getMyLeague().getTeams());
        }
    }

    private static void results(Team[] teams) {
        if (teams[0].getName().equals(TeamService.getMyTeam().getName())) {
            System.out.println("Congratulations, you won the championship!");
        } else {
            System.out.println("You didn't rank first. You still have nothing ahead of you!!");
        }
    }
}
