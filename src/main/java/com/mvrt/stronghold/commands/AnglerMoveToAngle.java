package com.mvrt.stronghold.commands;

import com.mvrt.lib.ConstantsBase;
import com.mvrt.lib.PidConstants;
import com.mvrt.lib.SynchronousPid;
import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AnglerMoveToAngle extends Command {

  boolean wantBrake = true;

  private PidConstants bottomUpPid, bottomDownPid, middleUpPid, middleDownPid, topPid;
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
        Constants.kAnglerMiddleDownKd);

    topPid = new PidConstants(Constants.kAnglerTopKp, Constants.kAnglerTopKi,
        Constants.kAnglerTopKd);


    pidController = new SynchronousPid(bottomUpPid);

    goal = angle;
  }
  @Override
  protected void initialize() {
    wantBrake = false;
    Robot.angler.brakeOff();

    pidController.setSetpoint(goal);
    pidController.setOutputRange(-0.8, 0.8);
  }

  public void setSetpoint(double goal) {
    pidController.setSetpoint(goal);
  }

  @Override
  protected void execute() {
    if (wantBrake) {
      Robot.angler.brakeOn();
      pidController.resetIntegrator();
    } else {
      Robot.angler.brakeOff();
    }

    double angle = Robot.angler.getAngle();

    boolean goingUp = angle >= goal;


    if (angle >= -20 && angle < 0) {
      pidController.setPid(goingUp ? bottomUpPid : bottomDownPid);
    } else if (angle >= -50 && angle < -20) {
      pidController.setPid(goingUp ? middleUpPid : middleDownPid);
    } else if(angle >= -113 && angle < -50) {
      pidController.setPid(topPid);
    }

    double output = pidController.calculate(angle);

    Robot.angler.setOutput(output);

    wantBrake = onTarget();
  }

  private boolean onTarget(){
    return Math.abs(pidController.getError()) <= Constants.kAnglerTolerance;
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    wantBrake = true;
    Robot.angler.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
