package com.mvrt.stronghold.commands;

/**
* Created by Ishan on 3/12/2016.
*/

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleBrake extends Command {

  boolean isFinished = false;

  public ToggleBrake() {
    requires(Robot.angler);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    Robot.angler.toggleBrake();
    isFinished = true;
  }

  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {}
}
