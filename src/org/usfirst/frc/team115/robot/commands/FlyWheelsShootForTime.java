package org.usfirst.frc.team115.robot.commands;

public class FlyWheelsShootForTime extends FlyWheelsShootForSpeed {

  private double seconds;

  public FlyWheelsShootForTime(double seconds) {
    this(FlyWheelsShootForSpeed.DEFAULT_SPEED, seconds);
  }

  public FlyWheelsShootForTime(double speed, double seconds) {
    super(speed);
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
