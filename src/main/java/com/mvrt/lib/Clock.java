package com.mvrt.lib;

import edu.wpi.first.wpilibj.Utility;

/**
 * A generalized way to measure system/fpga time with generators for each option.
 *
 * @author Lee Mracek
 */
public interface Clock {
  /**
   * Get the current clock time in microseconds.
   *
   * @return the long representation of the time in microseconds
   */
  long currentTimeInMicros();

  /**
   * Get the current clock time in nanoseconds.
   *
   * @return the long representation of time in nanoseconds
   */
  default long currentTimeInNanos() {
    return currentTimeInMicros() * 1000;
  }

  /**
   * Get the current clock time in milliseconds.
   *
   * @return the long representation of time in milliseconds
   */
  default long currentTimeInMillis() {
    return (long) (currentTimeInMicros() / 1000.0);
  }

  /**
   * Generator method which creates a clock using the FPGA chip on the RoboRIO.
   *
   * @return the Clock representing the FPGA
   */
  static Clock fpga() {
    try {
      Utility.getFPGATime();
      // if there is no exception, we have FPGA
      return Utility::getFPGATime;
    } catch (UnsatisfiedLinkError | NoClassDefFoundError e) {
      throw new RuntimeException("Missing FPGA hardware/software");
    }
  }

  /**
   * Generator method which creates a clock using the system time.
   *
   * @return the Clock representing the System time {@link System}
   */
  static Clock system() {
    return new Clock() {
      @Override
      public long currentTimeInMicros() {
        return (long) (currentTimeInNanos() / 1000.0);
      }

      @Override
      public long currentTimeInNanos() {
        return System.nanoTime();
      }

      @Override
      public long currentTimeInMillis() {
        return System.currentTimeMillis();
      }
    };
  }

  /**
   * Generator method which attempts to create an FPGA clock. If that fails because the FPGA does
   * not exist, it will default to the System clock.
   *
   * @return the Clock representing either the system or FPGA
   */
  static Clock fpgaOrSystem() {
    try {
      return fpga();
    } catch (RuntimeException e) {
      return system();
    }
  }
}
