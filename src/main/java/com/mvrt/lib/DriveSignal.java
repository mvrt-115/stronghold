package com.mvrt.lib;

/**
 * Created by lee on 1/21/16.
 */
public class DriveSignal {
  public double leftMotor, rightMotor;

  public DriveSignal(double leftMotor, double rightMotor) {
    this.leftMotor = leftMotor;
    this.rightMotor = rightMotor;
  }

  public static DriveSignal NEUTRAL = new DriveSignal(0.0, 0.0);
}
