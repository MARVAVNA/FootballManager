package model;

import java.util.Arrays;

public class League extends Football {
    private Team[] teams;
    private String name;

    public League(Team[] teams, String name) {
        this.teams = teams;
        this.name = name;
    }

    public Team[] getTeams() {
        return teams;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void play() {
        System.out.println("play");
    }

    @Override
    public String toString() {
        return "League{" +
                "teams=" + Arrays.toString(teams) +
                ", name='" + name + '\'' +
                '}';
    }
}