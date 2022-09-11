package de.exxcellent.challenge;

public class WeatherDatabase{

    private final String path = "src/main/resources/de/exxcellent/challenge/weather.csv";
    private int maxTemp = 1;
    private int minTemp = 2;

    public String getPathWeather() {
        return path;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }
}
