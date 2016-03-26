package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;

public class SetFlywheelPower extends Command {

  private Flywheel referenceFlywheel;

  private final double speed;

  private boolean finished = false;
  private boolean willTimeout = false;

  public SetFlywheelPower(Flywheel flywheel, double speed) {
    this.speed = speed;
    this.referenceFlywheel = flywheel;
    requires(flywheel);
  }

  public SetFlywheelPower(Flywheel flywheel, double speed, double timeout) {
    this.speed = speed;
    this.referenceFlywheel = flywheel;
    requires(flywheel);
  }

  @Override
  protected void initialize() {
    referenceFlywheel.getPIDController().disable();
  }

  @Override
  protected void execute() {
    referenceFlywheel.setSpeed(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    referenceFlywheel.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
