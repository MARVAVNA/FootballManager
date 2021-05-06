package services;

import custom.CustomFiles;
import custom.CustomNumber;
import custom.CustomString;
import model.League;
import model.Manager;
import model.Player;
import model.Team;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class LeagueService {
    private static LeagueService instance;
    private static League myLeague;
    private static String leagueName;

    private LeagueService() {

    }

    public static LeagueService getInstance() {
        if (instance == null) {
            instance = new LeagueService();
        }

        return instance;
    }

    public static void getStandings() {
        Team[] teams = sortStandings();
        System.out.println("----------------------------");
        for (Team team : teams) {
            System.out.printf("%d. %s %s %d\n", team.getPlaceInLeague(), team.getName(), team.getManager().getName(), team.getPoints());
        }
        System.out.println("----------------------------");
    }

    public static Team[] sortStandings() {
        Team[] teams = myLeague.getTeams();
        boolean check = true;
        int count = 0;
        while (check) {
            check = false;
            for (int i = 0; i < teams.length - 1 - count; i++) {
                if (teams[i + 1].getPoints() > teams[i].getPoints()) {
                    Team temp = teams[i];
                    teams[i] = teams[i + 1];
                    teams[i + 1] = temp;
                    check = true;
                }
            }
            count++;
        }

        for (int i = 0; i < teams.length; i++) {
            teams[i].setPlaceInLeague((byte)(i + 1));
        }

        return teams;
    }

    public void select() {
        String fileURI = String.format("%s%s%s%s%s", "src", File.separator, "TextDB", File.separator, "league.txt");
        File file = new File(fileURI);
        try {
            String fileContent = Files.readString(Paths.get(file.getPath()));
            String[] leagues = fileContent.split("\\n");

            String league = null;
            Scanner scanner = new Scanner(System.in);

            while (league == null) {
                printLeagueList(leagues);
                System.out.print("Select league: ");
                String select = scanner.next();
                if (CustomNumber.isInteger(select)) {
                    int selectInt = Integer.parseInt(select);
                    if (selectInt > 0 && selectInt <= leagues.length) {
                        league = leagues[selectInt - 1];
                    }
                }
            }

            leagueName = CustomString.replaceAllLineBreaksByEmpty(CustomString.replaceAllSpacesByEmpty(league));
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static String getName() {
        return leagueName;
    }

    public static void create() throws IOException {
        String leagueName = CustomString.replaceAllLineBreaksByEmpty(CustomString.replaceAllSpacesByEmpty(getName()));
        String directoryURI = String.format("%s%s%s%s%s%s", "src", File.separator, "TextDB", File.separator, leagueName, File.separator);
        String fileURI = String.format("%s%s", directoryURI, "2020-2021.txt");
        Object[] teamLines = CustomFiles.read(fileURI);

        Team[] teams = new Team[20];
        byte count = 0;
        for (Object team : teamLines) {
            String[] teamInfo = ((String) team).split(",");
            Object[] playerLines = CustomFiles.read(String.format("%s%s", directoryURI, CustomString.replaceAllSpacesByEmpty(teamInfo[1]) + ".txt"));
            Player[] players = new Player[11];
            for (int i = 0;  i < 11; i++) {
                players[i] = new Player(CustomString.replaceAllLineBreaksByEmpty((String) playerLines[i]));
            }
            teams[count] = new Team(teamInfo[1], players, new Manager(CustomString.replaceAllLineBreaksByEmpty((String) playerLines[11])));
            teams[count].setPlaceInLeague(Byte.parseByte(teamInfo[0]));
            teams[count].setPoints(Short.parseShort(CustomString.replaceAllLineBreaksByEmpty(teamInfo[3])));
            count++;
        }

        myLeague = new League(teams, leagueName);
    }

    public static League getMyLeague() {
        return myLeague;
    }

    private void printLeagueList(String[] leagues) {
        short count = 1;
        for (String league : leagues) {
            System.out.printf("%d. %s\n", count++, league);
        }
        System.out.println("----------------------------");
    }
}
