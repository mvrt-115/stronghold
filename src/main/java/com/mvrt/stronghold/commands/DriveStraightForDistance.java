package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

public class DriveStraightForDistance extends DriveStraightWithPID {

  private double distance;
  private double distLeft;

  public DriveStraightForDistance(double inches)
    this(Constants.kDriveDefaultSpeed, inches);
  }

  public DriveStraightForDistance(double speed, double inches) {
    super((inches < 0) ? speed :(-speed), false);
    this.distance = inches * Constants.kDriveDistancePerTick;
    distLeft = this.distance;
  }

  @Override
  protected void initialize() {
    super.initialize();
    Robot.drive.resetEncoders();
  }

  @Override
  protected void execute() {
    super.execute();
    distLeft = distance - Robot.drive.getAverageDistance();
    if (distLeft < 0)
      distleft = -distLeft;
  }

  @Override
  protected boolean isFinished() {
    return distLeft <= Constants.kDriveDistanceThreshold;
  }

}
