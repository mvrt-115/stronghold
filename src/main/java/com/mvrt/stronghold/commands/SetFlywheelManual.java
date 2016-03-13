package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import com.mvrt.stronghold.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Ishan on 3/13/2016.
 */
public class SetFlywheelManual extends Command {

  private final double speed;

  private boolean finished = false;

  public SetFlywheelManual(double speed) {
    this.speed = speed;

    requires(Robot.rightFlywheel);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.rightFlywheel.setSpeed(speed);
    Robot.leftFlywheel.setSpeed(-speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {

  }
}
