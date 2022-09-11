package de.exxcellent.challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Class with calculation function for determine the difference of two columns. */
public class Calculator {

  String path;
  String minuend;
  String subtrahend;
  int minuendIndex;
  int subtrahendIndex;
  ArrayList<String> minuendData = new ArrayList<>();
  ArrayList<String> subtrahendData = new ArrayList<>();
  ArrayList<String> result = new ArrayList<>();
  ArrayList<String> name;
  List<List<String>> records;

  public Calculator(String path, String minuend, String subtrahend) throws IOException {
    this.path = path;
    this.minuend = minuend;
    this.subtrahend = subtrahend;
    result.add("Difference"); // Adding this string to have the same row number + added values again

    CSVReader csvReader = new CSVReader(path);
    name = csvReader.getName();
    records = csvReader.getRecords();

    searchMinuend();
    searchSubtrahend();
    makeColumns();
  }

  /** Searches the index number of the minuend column. */
  private void searchMinuend() {
    if (records.get(0).contains(minuend)) {
      for (int i = 0; i < records.get(0).size(); i++) {
        if (minuend.equals(records.get(0).get(i))) {
          minuendIndex = i;
        }
      }
    } else {
      System.out.println("Wrong minuend name");
    }
  }

  /** Searches the index number of the subtrahend column. */
  private void searchSubtrahend() {
    if (records.get(0).contains(subtrahend)) {
      for (int i = 0; i < records.get(0).size(); i++) {
        if (subtrahend.equals(records.get(0).get(i))) {
          subtrahendIndex = i;
        }
      }
    } else {
      System.out.println("Wrong subtrahend name");
    }
  }

  /** Makes a minuend and subtrahend column with the needed values of the data (found in records) */
  private void makeColumns() {
    for (List<String> record : records) {
      minuendData.add(record.get(minuendIndex));
      subtrahendData.add(record.get(subtrahendIndex));
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
