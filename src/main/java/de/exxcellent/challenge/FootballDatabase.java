package de.exxcellent.challenge;

public class FootballDatabase{

    private final String path = "src/main/resources/de/exxcellent/challenge/football.csv";
    private int goalsAllowed = 6;
    private int Goals = 5;

    public String getPathFootball() {
        return path;
    }

    public int getGoalsAllowed() {
        return goalsAllowed;
    }

    public int getGoals() {
        return Goals;
    }
}
