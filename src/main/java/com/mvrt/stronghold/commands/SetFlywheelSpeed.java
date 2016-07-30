package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;

public class SetFlywheelSpeed extends Command {

  private Flywheel referenceFlywheel;

  private final double speed;

  private boolean finished = false;

  private boolean willTimeout = false;

  public SetFlywheelSpeed(Flywheel flywheel, double speed) {
    this.speed = speed;
    this.referenceFlywheel = flywheel;
    requires(flywheel);
  }

  public SetFlywheelSpeed(Flywheel flywheel, double speed, double timeout) {
    this.speed = speed;
    this.referenceFlywheel = flywheel;
    setTimeout(timeout);
    willTimeout = true;
    requires(flywheel);
  }

  @Override
  protected void initialize() {
    finished = false;
    referenceFlywheel.disable();
  }

  @Override
  protected void execute() {
    if (referenceFlywheel.isNearTarget()) {
      referenceFlywheel.getPIDController().setPID(Constants.kFlywheelKp, Constants.kFlywheelKi,
              Constants.kFlywheelKd);
    } else {
      referenceFlywheel.getPIDController().setPID(Constants.kFlywheelKpOnTarget,
              Constants.kFlywheelKiOnTarget, Constants.kFlywheelKdOnTarget);
    }
    if(!finished) {
      //referenceFlywheel.setSpeed(speed);
      referenceFlywheel.setSetpoint(speed);
      referenceFlywheel.enable();
      finished = true;
    }
  }

  @Override
  protected boolean isFinished() {
    return finished && !willTimeout;
  }

  @Override
  protected void end() {
    if (willTimeout) {
      referenceFlywheel.stop();
    }
  }

  @Override
  protected void interrupted() {}
}
