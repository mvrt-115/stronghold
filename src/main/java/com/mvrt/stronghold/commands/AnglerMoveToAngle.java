/*package com.mvrt.stronghold.commands;

import com.mvrt.lib.ConstantsBase;
import com.mvrt.lib.PidConstants;
import com.mvrt.lib.SynchronousPid;
import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import edu.wpi.first.wpilibj.command.Command;

public class AnglerMoveToAngle extends Command {

  boolean wantBrake = true;

  private PidConstants bottomUpPid, bottomDownPid, middleUpPid, middleDownPid;
  private SynchronousPid pidController;

  private final double goal;

  public AnglerMoveToAngle(double angle) {
    requires(Robot.angler);

    bottomUpPid = new PidConstants(Constants.kAnglerBottomUpKp, Constants.kAnglerBottomUpKi,
        Constants.kAnglerBottomUpKd);
    bottomDownPid = new PidConstants(Constants.kAnglerBottomDownKp, Constants.kAnglerBottomDownKi,
        Constants.kAnglerBottomDownKd);
    middleUpPid = new PidConstants(Constants.kAnglerMiddleUpKp, Constants.kAnglerMiddleUpKi,
        Constants.kAnglerMiddleUpKd);
    middleDownPid = new PidConstants(Constants.kAnglerMiddleDownKp, Constants.kAnglerMiddleDownKi,
        Constants.kAnglerBottomUpKd);

    pidController = new SynchronousPid(bottomUpPid);

    goal = angle;
  }
  @Override
  protected void initialize() {
    wantBrake = false;
    Robot.angler.brakeOff();

    pidController.setSetpoint(goal);
  }

  @Override
  protected void execute() {
    if (wantBrake && !Robot.angler.isBraked()) {
      Robot.angler.brakeOn();
    } else if (!wantBrake && Robot.angler.isBraked()) {
      Robot.angler.brakeOff();
    }

    double angle = Robot.angler.getAngle();

    boolean goingUp = angle <= goal;

    if (angle >= -40 && angle < 50) {
      pidController.setPid(goingUp ? bottomUpPid : bottomDownPid);
    } else if (angle >= 50 && angle < 113) {
      pidController.setPid(goingUp ? middleUpPid : middleDownPid);
    }

    pidController.calculate(angle);

    Robot.angler.setOutput(pidController.retrieve());

    if (isFinished()) {
      wantBrake = true;
    }
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(goal - Robot.angler.getAngle()) <= 3;
  }

  @Override
  protected void end() {
    wantBrake = true;
    Robot.angler.brakeOn();
  }

  @Override
  protected void interrupted() {
    end();
  }
}*/
