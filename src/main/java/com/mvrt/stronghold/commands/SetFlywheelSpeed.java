package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;

public class SetFlywheelSpeed extends Command {

  private Flywheel referenceFlywheel;

  private final double speed;
  
  private boolean finished = false;
  public SetFlywheelSpeed(Flywheel flywheel, double speed) {
    this.speed = speed;
    this.referenceFlywheel = flywheel;
    this.referenceFlywheel.overCompensate(0);

    requires(flywheel);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
	if(referenceFlywheel.isNearTarget()){
		referenceFlywheel.getPIDController().setPID(Constants.kFlywheelKp, Constants.kFlywheelKi, Constants.kAnglerBottomDownKd);
		referenceFlywheel.overCompensate(Constants.kFlywheelOvercompensation);
	}
	else{
		referenceFlywheel.getPIDController().setPID(Constants.kFlywheelKpOnTarget, Constants.kFlywheelKiOnTarget, Constants.kFlywheelKdOnTarget);
	    referenceFlywheel.overCompensate(0);
	}
    referenceFlywheel.setSetpoint(speed);
    referenceFlywheel.enable();
    //finished = true;
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
