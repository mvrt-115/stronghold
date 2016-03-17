package com.mvrt.stronghold.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command {

  private double delay;

  public Delay(double delay) {
    this.delay = delay;
  }

  @Override
  protected void initialize() {
    setTimeout(delay);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {return false;}

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {
    end();
  }
}
