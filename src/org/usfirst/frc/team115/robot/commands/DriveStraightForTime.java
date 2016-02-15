package org.usfirst.frc.team115.robot.commands;

/**
 * 
 * 
 * @author Heather Baker
 */

public class DriveStraightForTime extends DriveStraight {
  
  private double seconds;
  
  public DriveStraightForTime(double seconds) {
    this(DriveStraight.DEFAULT_SPEED, seconds);
  }
  
  public DriveStraightForTime(double speed, double seconds) {
    super(speed, false);
    this.seconds = seconds;
  }
  
  @Override
  protected void initialize() {
    super.initialize();
    setTimeout(seconds);
  }
  
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

}
