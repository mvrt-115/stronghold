package com.mvrt.lib;

import java.util.function.DoubleFunction;

/**
 * Class containing various static utility methods and generators daeling with numerical values.
 *
 * @author Lee Mracek
 */
public class Values {
  private static final int DEFAULT_NUMBER_OF_BITS = 12;

  /**
   * Compare two numbers to within a certain number of bits, defaulting to 12 bits.
   *
   * @param a the first number
   * @param b the second number
   * @return 0 if the numbers are the same, 1 if a is greater and -1 if b is greater
   * @see #fuzzyCompare(double, double, int)
   */
  public static int fuzzyCompare(double a, double b) {
    return fuzzyCompare(a, b, DEFAULT_NUMBER_OF_BITS);
  }

  /**
   * Compare two numbers to within a certain number of bits, defaulting to 12 bits.
   *
   * @param a    the first number
   * @param b    the second number
   * @param bits the number of bits of accuracy desired
   * @return 0 if the numbers are the same, 1 if a is greater and -1 if b is greater
   */
  public static int fuzzyCompare(double a, double b, int bits) {
    return fuzzyCompare(a, b, calcTolerance(bits));
  }

  private static int fuzzyCompare(double a, double b, double tolerance) {
    if (tolerance < 0.0) {
      throw new IllegalArgumentException("The tolerance must not be negative");
    }
    double difference = a - b;
    return Math.abs(difference) <= tolerance ? 0 : (int) Math.signum(difference);
  }

  private static double calcTolerance(int bits) {
    return 1.0 / (1 << bits);
  }

  /**
   * Construct and return an {@link DoubleFunction} which limits a value to between two other
   * values.
   *
   * @param minimum the minimum possible value
   * @param maximum the maximum possible value; must be greater the minimum
   * @return the limiting function
   */
  public static DoubleFunction<Double> limiter(double minimum, double maximum) {
    if (maximum < minimum) {
      throw new IllegalArgumentException("The minimum value cannot exceed the maximum value");
    }

    return (double value) -> {
      if (value > maximum) {
        return maximum;
      }
      if (value < minimum) {
        return minimum;
      }
      return value;
    };
  }
}
