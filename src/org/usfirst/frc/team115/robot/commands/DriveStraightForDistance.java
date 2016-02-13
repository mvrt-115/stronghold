package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

/**
 * 
 * 
 * @author Heather Baker
 */

public class DriveStraightForDistance extends DriveStraight {

  private final double THRESHOLD = 0.05;
  private final double TICKS_PER_ROTATION = 1444;
  private final double TICKS_PER_INCH = (1 / (Math.PI * 8)) * 2 * TICKS_PER_ROTATION;
  private double distance;
  private double distLeft;
  
  public DriveStraightForDistance(double inches) {
    this(DriveStraight.DEFAULT_SPEED, inches);
  }
  
  public DriveStraightForDistance(double speed, double inches) {
    super((inches < 0) ? speed:(-speed), false);
    this.distance = inches * TICKS_PER_INCH;
    distLeft = this.distance;
  }
  
  @Override
  protected void initialize() {
    super.initialize();
    Robot.drive.resetEncoders();
  }
  
  protected double absoluteDistanceLeft() {
	  distLeft = distance - Robot.drive.getDistance();
	  if (distLeft < 0)
		  return -distLeft;
	  return distLeft;
  }
  
  @Override
  protected boolean isFinished() {
    return absoluteDistanceLeft() <= THRESHOLD;
  }

}
