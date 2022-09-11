package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

  String path;
  String[] data;
  String line;
  ArrayList<String> name = new ArrayList<>();
  List<List<String>> records = new ArrayList<>();

  /**
   * This class reads csv files.
   *
   * @param path of the file.
   * @throws IOException If the file can not be read.
   */
  public CSVReader(String path) throws IOException {
    this.path = path;

    BufferedReader br = new BufferedReader(new FileReader(path));
    while ((line = br.readLine()) != null) {
      data = line.split(",");
      records.add(Arrays.asList(data));
      name.add(data[0]);
    }
  }

  /**
   * Get method for an that stores all data of the first column.
   *
   * @return Array with names of the first column of the csv file.
   */
  public ArrayList<String> getName() {
    return name;
  }

  /**
   * Get method for a list that stores all csv data.
   *
   * @return List with all csv data.
   */
  public List<List<String>> getRecords() {
    return records;
  }
}
