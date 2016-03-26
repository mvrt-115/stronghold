package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Ishan on 3/13/2016.
 */
public class Intake extends Command {

  public Intake() {

    requires(Robot.intake);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.rightFlywheel.setSpeed(Constants.kShooterIntakePreset);
    Robot.leftFlywheel.setSpeed(Constants.kShooterIntakePreset);
    Robot.intake.setOutput(-0.35);
  }

  @Override
  protected boolean isFinished() {
    return !(Robot.operatorInterface.getDriveJoystick()
        .getRawButton(Constants.kDriveIntakeButton)); //replace with operator part
  }

  @Override
  protected void end() {
    Robot.rightFlywheel.setSpeed(0);
    Robot.leftFlywheel.setSpeed(0);
    Robot.intake.setOutput(0);
  }

  @Override
  protected void interrupted() {

  }
}
