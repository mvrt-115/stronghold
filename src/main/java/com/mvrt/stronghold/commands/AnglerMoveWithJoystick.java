package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AnglerMoveWithJoystick extends Command {

  public AnglerMoveWithJoystick(){
    requires(Robot.angler);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    // should use operator joystick?
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.angler.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
