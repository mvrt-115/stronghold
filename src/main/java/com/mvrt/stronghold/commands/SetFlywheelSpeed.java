package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import com.mvrt.stronghold.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetFlywheelSpeed extends Command {

  private Flywheel referenceFlywheel;

  private final double speed;

  private boolean finished = false;

  public SetFlywheelSpeed(Flywheel flywheel) {//Use this to just blank fire and then stop
    this.speed = 25;
    this.referenceFlywheel = flywheel;

    finished = true;
    requires(flywheel);
  }

  public SetFlywheelSpeed(Flywheel flywheel, double speed) {
    this.speed = speed;
    this.referenceFlywheel = flywheel;

    finished = false;
    requires(flywheel);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    if(referenceFlywheel.isNearTarget()){
      referenceFlywheel.getPIDController().setPID(Constants.kFlywheelKp, Constants.kFlywheelKi, Constants.kAnglerBottomDownKd);
    }
    else{
      referenceFlywheel.getPIDController().setPID(Constants.kFlywheelKpOnTarget, Constants.kFlywheelKiOnTarget, Constants.kFlywheelKdOnTarget);
    }
    referenceFlywheel.setSetpoint(speed);
    referenceFlywheel.enable();
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
