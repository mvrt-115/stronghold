package com.mvrt.stronghold.commands;

import com.mvrt.lib.ConstantsBase;
import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class AnglerMoveToAngle extends PIDCommand{

  public AnglerMoveToAngle(double setpoint) {
    super(Constants.kAnglerKp, Constants.kAnglerKi, Constants.kAnglerKd);
    requires(Robot.angler);
    getPIDController().setInputRange(Constants.kIntakePreset, Constants.kBatterPreset);
    setSetpoint(setpoint);
    getPIDController().enable();
  }

  @Override
  protected double returnPIDInput() {
    return Robot.angler.getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.angler.setOutput(output);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {}

  @Override
  protected boolean isFinished() {
    return Math.abs(getPosition() - getSetpoint()) < 1;
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
