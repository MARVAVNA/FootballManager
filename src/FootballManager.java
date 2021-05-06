import model.League;
import model.Player;
import model.Team;
import services.LeagueService;
import services.MenuService;
import services.TeamService;

import java.io.IOException;

public class FootballManager {
    public static void main(String[] args) {
        LeagueService leagueService = LeagueService.getInstance();
        leagueService.select();

        TeamService teamService = TeamService.getInstance(LeagueService.getName());
        teamService.select();
        try {
            teamService.create();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            LeagueService.create();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MenuService.createMenu();
    }
}
