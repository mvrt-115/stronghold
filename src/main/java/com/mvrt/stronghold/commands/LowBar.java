package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBar extends Command {

  public LowBar() {
    requires(Robot.angler);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.angler.setOutput(0.4);
  }

  @Override
  protected boolean isFinished() {
    return Robot.angler.getAngle() >= 85;
  }

  @Override
  protected void end() {
    Robot.angler.setOutput(0);
    Robot.angler.brakeOff();
  }

  @Override
  protected void interrupted() {

  }

}
