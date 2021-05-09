package services;

import custom.CustomNumber;
import custom.CustomString;
import model.Player;
import model.Team;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TeamService {
    private static TeamService instance;
    private static String leagueName;
    private static String team;
    private static Team myTeam;

    private TeamService(String leagueName) {
        TeamService.leagueName = leagueName;
    }

    public void create() throws IOException {
        String leagueName = CustomString.replaceAllLineBreaksByEmpty(CustomString.replaceAllSpacesByEmpty(TeamService.getLeagueName()));
        String[] teamInfo = team.split(",");
        String teamName = teamInfo[1];
        String fileURI = new StringBuilder("src")
                .append("\\TextDB\\")
                .append(leagueName)
                .append("\\")
                .append(CustomString.replaceAllLineBreaksByEmpty(CustomString.replaceAllSpacesByEmpty(teamName)))
                .append(".txt")
                .toString();
        Object[] lines = Files.readAllLines(Paths.get(fileURI)).toArray();
        Player[] players = new Player[11];
        for (int i = 0; i < 11; i++) {
            players[i] = PlayerService.create((String) lines[i]);
        }

        myTeam = new Team(teamName, players, ManagerService.create((String) lines[11]));
        myTeam.setPoints(Short.parseShort(CustomString.replaceAllLineBreaksByEmpty(teamInfo[3])));
        myTeam.setPlaceInLeague(Byte.parseByte(teamInfo[0]));
    }

    public static TeamService getInstance(String leagueName) {
        if (instance == null) {
            instance = new TeamService(leagueName);
        }

        return instance;
    }

    public void select() {
        String leagueFDirectoryName = CustomString.replaceAllSpacesByEmpty(leagueName);
        leagueFDirectoryName = CustomString.replaceAllLineBreaksByEmpty(leagueFDirectoryName);
        String fileURI = new StringBuilder("src")
                .append(File.separator)
                .append("TextDB")
                .append(File.separator)
                .append(leagueFDirectoryName)
                .append(File.separator)
                .append("2020-2021.txt")
                .toString();
        File file = new File(fileURI);
        try {
            String fileContent = Files.readString(Paths.get(file.getPath()));
            String[] teams = fileContent.split("\\n");

            String team = null;
            Scanner scanner = new Scanner(System.in);

            while (team == null) {
                printTeamList(teams);
                System.out.print("Select team: ");
                String select = scanner.next();
                if (CustomNumber.isInteger(select)) {
                    int selectInt = Integer.parseInt(select);
                    if (selectInt > 0 && selectInt <= teams.length) {
                        team = teams[selectInt - 1];
                    }
                }
            }

            TeamService.team = team;
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    private void printTeamList(String[] teams) {
        short count = 1;
        System.out.println("----------------------------");
        for (String line : teams) {
            String[] teamInfo = line.split(",");
            System.out.printf("%d. %s\n", count++, teamInfo[1]);
        }
        System.out.println("----------------------------");
    }

    public static Team getMyTeam() {
        return myTeam;
    }

    public static void setMyTeam(Team myTeam) {
        if (TeamService.myTeam == null) {
            TeamService.myTeam = myTeam;
        } else {
            System.out.printf("Your team already %s\n", TeamService.myTeam.getName());
        }
    }

    public static String getLeagueName() {
        return leagueName;
    }
}
