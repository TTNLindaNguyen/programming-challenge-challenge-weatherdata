package de.exxcellent.challenge;

import java.io.IOException;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as
 * baseline for your software design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

  /**
   * This is the main entry method of your program.
   *
   * @param args The CLI arguments passed
   */
  public static void main(String... args) throws IOException {

    // Your preparation code …

    WeatherDatabase weatherDatabase = new WeatherDatabase();
    FootballDatabase footballDatabase = new FootballDatabase();

    CSVReader csvReaderWeather =
        new CSVReader(
            weatherDatabase.getPathWeather(),
            weatherDatabase.getMaxTemp(),
            weatherDatabase.getMinTemp());

    CSVReader csvReaderFootball =
        new CSVReader(
            footballDatabase.getPathFootball(),
            footballDatabase.getGoals(),
            footballDatabase.getGoalsAllowed());

    String dayWithSmallestTempSpread =
        csvReaderWeather.giveResult("weather"); // Your day analysis function call …
    System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

    String teamWithSmallestGoalSpread =
        csvReaderFootball.giveResult("football"); // Your goal analysis function call …
    System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
  }
}
