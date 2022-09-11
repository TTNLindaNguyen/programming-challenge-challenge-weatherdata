package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CSVReader {

  String path;
  String[] data;
  String line;
  ArrayList<String> name = new ArrayList<>();
  ArrayList<String> minuendData = new ArrayList<>();
  ArrayList<String> subtrahendData = new ArrayList<>();
  ArrayList<String> result = new ArrayList<>();

  int minuend;
  int subtrahend;

  public CSVReader(String path, int minuend, int subtrahend) throws IOException {
    this.path = path;
    this.minuend = minuend;
    this.subtrahend = subtrahend;

    result.add("Difference");
    BufferedReader br = new BufferedReader(new FileReader(path));
    while ((line = br.readLine()) != null) {
      data = line.split(",");
      name.add(data[0]);
      minuendData.add(data[minuend]);
      subtrahendData.add(data[subtrahend]);
    }

  }

  private void subtractColumns() {
    for (int i = 1; i < minuendData.size(); i++) {
      int minuend = Integer.parseInt(minuendData.get(i));
      int subtrahend = Integer.parseInt(subtrahendData.get(i));
      int difference = minuend - subtrahend;
      result.add(Integer.toString(difference));
    }
  }

  /**
   * Determines the lowest value in the ArrayList. Adding 1 to ignore the first String index when
   * calculating.
   *
   * @return the day with the lowest value.
   */
  private String minDif() {
    return name.get(result.indexOf(Collections.min(result)) + 1);
  }

  /**
   * Determines the index for the smallest absolute difference in the ArrayList. Adding 1 to ignore the first
   * String index when calculating.
   *
   * @return the football team with the smallest absolute difference.
   */
  private String absoluteDif() {
    ArrayList<Integer> intList = new ArrayList<>();
    for (int i = 1; i < result.size(); i++) {
      if (Integer.parseInt(result.get(i)) < 0) {
        int a = Integer.parseInt(result.get(i)) * (-1);
        intList.add(a);
      } else {
        intList.add(Integer.parseInt(result.get(i)));
      }
    }
    return name.get(intList.indexOf(Collections.min(intList)) + 1);
  }

  public String giveResult(String category) {
    subtractColumns();

    switch (category) {
      case "weather":
        return minDif();
      case "football":
        return absoluteDif();
      default:
        return "Error";
    }
  }
}
