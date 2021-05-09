package model;

public class League {
    private Team[] teams;
    private String name;
    private static boolean nullify;

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

    public static boolean isNullify() {
        return nullify;
    }

    public static void setNullify(boolean nullify) {
        League.nullify = nullify;
    }

}