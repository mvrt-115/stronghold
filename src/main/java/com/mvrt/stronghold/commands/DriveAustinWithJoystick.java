package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveAustinWithJoystick extends Command {

  public DriveAustinWithJoystick() {
    requires(Robot.drive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double throttle = Robot.oi.getDriveJoystick().getThrottle();
    double wheel = Robot.oi.getDriveJoystick().getX();
    boolean quickturn = Robot.oi.getDriveJoystick().getTrigger();
    Robot.drive.drive(throttle, wheel, quickturn);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.drive.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
