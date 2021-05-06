package model;

import services.TeamService;

public class Team {
    private String name;
    private Player[] players;
    private Manager manager;
    private byte placeInLeague;
    private short points;

    public Team(String name, Player[] players, Manager manager) {
        this.name = name;
        this.players = players;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public byte getPlaceInLeague() {
        return placeInLeague;
    }

    public void setPlaceInLeague(byte placeInLeague) {
        this.placeInLeague = placeInLeague;
    }

    public short getPoints() {
        return points;
    }

    public void setPoints(short points) {
        this.points = points;
    }

    public void printInfo() {
        System.out.println("----------------------------");
        System.out.printf("League: %s\n", TeamService.getLeagueName());
        System.out.printf("Team name: %s\n", getName());
        System.out.printf("Manager: %s\n", getManager().getName());
        System.out.printf("League position: %d\n", getPlaceInLeague());
        System.out.printf("Points: %d\n", getPoints());
        System.out.println("----------------------------");
    }
}
