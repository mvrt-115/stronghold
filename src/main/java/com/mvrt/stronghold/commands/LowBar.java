package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class LowBar extends Command {

  public LowBar() {
    setTimeout(0.45);
    requires(Robot.angler);
  }

  @Override
  protected void initialize() {
    Robot.angler.brakeOff();
  }

  @Override
  protected void execute() {
    Robot.angler.setOutput(-0.65);
  }

  @Override
  protected boolean isFinished() {
    return  isTimedOut() || Robot.angler.isBottomLimit();
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
