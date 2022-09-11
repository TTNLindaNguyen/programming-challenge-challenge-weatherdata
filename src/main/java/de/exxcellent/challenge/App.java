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
    final String pathWeather = "src/main/resources/de/exxcellent/challenge/weather.csv";
    final String pathFootball = "src/main/resources/de/exxcellent/challenge/football.csv";

    Calculator calculatorWeather = new Calculator(pathWeather, "MxT", "MnT");

    Calculator calculatorFootball = new Calculator(pathFootball, "Goals", "Goals Allowed");

    String dayWithSmallestTempSpread =
        calculatorWeather.giveResult("difference"); // Your day analysis function call …
    System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

    String teamWithSmallestGoalSpread =
        calculatorFootball.giveResult("absolute difference"); // Your goal analysis function call …
    System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
  }
}
