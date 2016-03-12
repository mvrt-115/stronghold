package com.mvrt.lib;

import java.util.concurrent.TimeUnit;

/**
 * A class which 'ticks' at a given rate.
 *
 * @author Lee Mracek
 */
public interface Metronome {

  /**
   * Pause until the next tick of the metronome.
   *
   * @return true if the pause completed successfully
   */
  boolean pause();

  /**
   * Construct a new Metronome using the 'busy' method.
   * <p>
   * Essentially, an empty while loop holds thread context and forces the wait.
   *
   * @param period the period at which the metronome ticks
   * @param unit   the {@link TimeUnit} of the period
   * @param time   the Clock used for the metronome
   * @return the constructed Metronome
   */
  static Metronome metronome(long period, TimeUnit unit, Clock time) {
    long periodInNanos = unit.toNanos(period);

    return new Metronome() {
      private long next = time.currentTimeInNanos() + periodInNanos;

      @Override
      public boolean pause() {
        while (next - time.currentTimeInNanos() >= 0) {
        }

        next = next + periodInNanos;
        return true;
      }

      @Override
      public String toString() {
        return "Metronome (busy period " + TimeUnit.NANOSECONDS.toMillis(periodInNanos) + " ms)";
      }
    };
  }
}
