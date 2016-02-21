package com.mvrt.lib;

/**
 * Created by lee on 2/17/16.
 */
public class PidConstants {
  public final double kP, kI, kD;
  public PidConstants(double kP, double kI, double kD) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
  }
}
