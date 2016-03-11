package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by MVRT on 3/10/2016.
 */
public class ExtendClimber extends Command {

  boolean isFinished = false;

  public ExtendClimber() {
    requires(Robot.climber);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.climber.extendClimber();
    isFinished = true;
  }

  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {

  }
}
