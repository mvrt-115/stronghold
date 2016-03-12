package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;

public class SetFlywheelSpeed extends Command {

  private Flywheel referenceFlywheel;

  private final double speed;

  private boolean finished = false;

  public SetFlywheelSpeed(Flywheel flywheel, double speedMs) {
    this.speed = speedMs;
    this.referenceFlywheel = flywheel;

    requires(flywheel);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    // referenceFlywheel.setSetpoint(speed);
    // referenceFlywheel.enable();
    referenceFlywheel.setGoal(speed);
    finished = true;
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {

  }
}
