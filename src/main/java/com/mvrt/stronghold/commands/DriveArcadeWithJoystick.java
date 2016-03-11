package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveArcadeWithJoystick extends Command {

  private double scalar = 1.0;

  public DriveArcadeWithJoystick() {
    this(false, false);
  }

  public DriveArcadeWithJoystick(boolean invert, boolean precision) {
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
    double move = scalar * Robot.operatorInterface.getDriveJoystick().getY();
    double rotate = scalar * Robot.operatorInterface.getDriveJoystick().getX();
    Robot.drive.drive(move, rotate);
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
