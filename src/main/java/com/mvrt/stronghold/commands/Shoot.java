package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {

  public Shoot() {
    requires(Robot.punch);
  }

  @Override
  protected void initialize() {
    setTimeout(0.5);
    Robot.punch.extend();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.punch.retract();
    Robot.leftFlywheel.stop();
    Robot.rightFlywheel.stop();
    // Robot.leftFlywheel.disable();
    // Robot.rightFlywheel.disable();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
