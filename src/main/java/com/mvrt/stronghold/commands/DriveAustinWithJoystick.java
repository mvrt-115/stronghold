package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveAustinWithJoystick extends Command {

  private double scalar = 1.0;

  public DriveAustinWithJoystick() {
    this(false, false);
  }

  public DriveAustinWithJoystick(boolean invert, boolean precision) {
    requires(Robot.drive);
    if (precision) {
      scalar = Constants.kDrivePrecision;
    }
    if (invert) {
      scalar = -scalar;
    }
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double throttle = Robot.operatorInterface.getDriveJoystick().getRawButton(2)
            ? Robot.operatorInterface.getDriveJoystick().getY() * 0.35
            : Robot.operatorInterface.getDriveJoystick().getY();
    double wheel = Robot.operatorInterface.getDriveJoystick().getX();
    boolean quickturn = Robot.operatorInterface.getDriveJoystick().getTrigger();
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
