package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command {

  double time;

  public Delay(double time) {
    this.time = time;
  }

  @Override
  protected void initialize() {
    setTimeout(time);
  }

  @Override
  protected void execute() {}

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {}
}
