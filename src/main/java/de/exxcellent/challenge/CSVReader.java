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
  List<List<String>> records = new ArrayList<>();

  String minuend;
  String subtrahend;

  /**
   * This class can read csv files and calculates differences of two columns.
   *
   * @param path of the file.
   * @param minuend The column, which will be subtracted from.
   * @param subtrahend The column, which will be the subtrahend.
   * @throws IOException If the file can not be read.
   */
  public CSVReader(String path, String minuend, String subtrahend) throws IOException {
    this.path = path;
    this.minuend = minuend;
    this.subtrahend = subtrahend;

    result.add("Difference"); // Adding this string to have the same row number + added values again
    BufferedReader br = new BufferedReader(new FileReader(path));
    while ((line = br.readLine()) != null) {
      data = line.split(",");
      records.add(Arrays.asList(data));
      name.add(data[0]);
    }

    searchMinuend();
    searchSubtrahend();
    makeColumns();
  }

  /**
   * Searches the index number of the minuend column.
   *
   * @return index of the minuend column.
   */
  private int searchMinuend() {
    int index = 0;
    for (int i = 0; i < records.get(0).size(); i++) {
      if (minuend.equals(records.get(0).get(i))) {
        index = i;
        return index;
      }
    }
    return index;
  }

  /**
   * Searches the index number of the subtrahend column.
   *
   * @return index of the subtrahend column.
   */
  private int searchSubtrahend() {
    int index = 0;
    for (int i = 0; i < records.get(0).size(); i++) {
      if (subtrahend.equals(records.get(0).get(i))) {
        index = i;
        return index;
      }
    }
    return index;
  }

  /** Makes a minuend and subtrahend column with the needed values of the data (found in records) */
  private void makeColumns() {
    for (List<String> record : records) {
      minuendData.add(record.get(searchMinuend()));
      subtrahendData.add(record.get(searchSubtrahend()));
    }
  }

  /** Subtraction of minuend and subtrahend columns. */
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
   * @return the name with the lowest value.
   */
  private String minDif() {
    return name.get(result.indexOf(Collections.min(result)) + 1);
  }

  /**
   * Determines the index for the smallest absolute difference in the ArrayList. Adding 1 to ignore
   * the first String index when calculating.
   *
   * @return the name with the smallest absolute difference.
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

  /**
   * Gives the result of the calculation.
   *
   * @param category calculated difference or absolute difference.
   * @return a result.
   */
  public String giveResult(String category) {
    subtractColumns();

    switch (category) {
      case "difference":
        return minDif();
      case "absolute difference":
        return absoluteDif();
      default:
        return "Error";
    }
  }
}
