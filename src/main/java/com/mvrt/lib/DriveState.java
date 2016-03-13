package com.mvrt.lib;

/**
 * A representation of the robot's drive state at a given time containing both distance and
 * velocity information. This class should comprehensively represent the robot's present
 * direction, speed, and distances.
 *
 * @author Lee Mracek
 */
public class DriveState {
  private double leftDistance;
  private double rightDistance;
  private double leftVelocity;
  private double rightVelocity;
  private double heading;
  private double headingVelocity;

  /**
   * Construct a new DriveState object for the given data.
   *
   * @param leftDistance the distance traveled by the left side
   * @param rightDistance the distance traveled by the right side
   * @param leftVelocity the velocity of the left side
   * @param rightVelocity the velocity of the right side
   * @param heading the angle of the gyroscope
   * @param headingVelocity the rate of change of measured gyroscope angle
   */
  public DriveState(double leftDistance, double rightDistance, double leftVelocity,
      double rightVelocity, double heading, double headingVelocity) {
    this.leftDistance = leftDistance;
    this.rightDistance = rightDistance;
    this.leftVelocity = leftVelocity;
    this.rightVelocity = rightVelocity;
    this.heading = heading;
    this.headingVelocity = headingVelocity;
  }

  /**
   * Flash the existing DriveState object for the given data.
   *
   * @param leftDistance the distance traveled by the left side
   * @param rightDistance the distance traveled by the right side
   * @param leftVelocity the velocity of the left side
   * @param rightVelocity the velocity of the right side
   * @param heading the angle of the gyroscope
   * @param headingVelocity the rate of change of measured gyroscope angle
   */
  public void reset(double leftDistance, double rightDistance, double leftVelocity,
      double rightVelocity, double heading, double headingVelocity) {
    this.leftDistance = leftDistance;
    this.rightDistance = rightDistance;
    this.leftVelocity = leftVelocity;
    this.rightVelocity = rightVelocity;
    this.heading = heading;
    this.headingVelocity = headingVelocity;
  }

  /**
   * Retrieve the left distance.
   *
   * @return the left encoder distance
   */
  public double getLeftDistance() {
    return leftDistance;
  }

  /**
   * Retrieve the right distance.
   *
   * @return the right encoder distance
   */
  public double getRightDistance() {
    return rightDistance;
  }

  /**
   * Retrieve the left encoder velocity.
   *
   * @return the left encoder velocity
   */
  public double getLeftVelocity() {
    return leftVelocity;
  }

  /**
   * Retrieve the right encoder velocity.
   *
   * @return the right encoder velocity
   */
  public double getRightVelocity() {
    return rightVelocity;
  }

  /**
   * Retrieve the current robot heading.
   *
   * @return the current robot heading
   */
  public double getHeading() {
    return heading;
  }

  /**
   * Retrieve the current rate of change in heading.
   *
   * @return the rate of change in heading
   */
  public double getHeadingVelocity() {
    return headingVelocity;
  }
}
