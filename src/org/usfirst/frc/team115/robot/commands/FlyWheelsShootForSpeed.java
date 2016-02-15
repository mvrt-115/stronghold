package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FlyWheelsShootForSpeed extends Command {

  private double speed;
  protected final static double DEFAULT_SPEED = 0.6;

  public FlyWheelsShootForSpeed() {
    this(DEFAULT_SPEED);
  }

  public FlyWheelsShootForSpeed(double speed) {
    requires(Robot.flyWheels);
    this.speed = speed;
  }

  @Override
  protected void initialize() {
    Robot.flyWheels.startPID(speed);
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.flyWheels.resetPID();
    Robot.flyWheels.resetEncoders();
  }

  @Override
  protected void interrupted() {
    end();
  }

}
