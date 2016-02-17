package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Samster on 2/16/2016.
 */
public class Shoot extends Command {

  public Shoot(){
    requires(Robot.punch);
  }

  @Override
  protected void initialize() {
    setTimeout(0.5);
    Robot.punch.extend();
  }

  @Override
  protected void execute() {}

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.punch.retract();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
