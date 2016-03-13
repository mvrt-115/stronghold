package com.mvrt.lib;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Simple writer for logging to CSV files.
 *
 * @author Lee Mracek
 */
public class CsvWriter extends FileWriter {

  int index;

  /**
   * Construct a new CsvWriter from a file and a set of header data.
   *
   * @param file the file for output
   * @param headerData the header to add to the file (for input into Excel, etc)
   * @throws IOException if the file does not exist or is not reachable
   */
  public CsvWriter(File file, String... headerData) throws IOException {
    super(file);
    if (headerData.length != 0) {
      index = headerData.length;
      StringBuilder head = new StringBuilder();
      for (String header : headerData) {
        head.append(header + ",");
      }
      super.write(head.substring(0, head.length() - 1) + "\n");
      super.flush();
    } else {
      index = -1;
    }
  }

  /**
   * Write a single line of data with the same length as the header data. Each number is comma
   * delimited.
   *
   * @param data the numerical data to write
   */
  public void writeLine(Number... data) {
    if (data.length != 0 && (data.length == index || index == -1)) {
      StringBuilder head = new StringBuilder();
      for (Number header : data) {
        head.append(header + ",");
      }
      try {
        super.write(head.substring(0, head.length() - 1) + "\n");
        super.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      throw new IllegalArgumentException("Number of parameters must match CSV data");
    }
  }
}