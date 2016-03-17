package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToAngle extends Command {
  public MoveToAngle() {
    requires(Robot.angler);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    Robot.angler.brakeOff();
    Robot.angler.setOutput(Robot.operatorInterface
            .getOperatorJoystick().getRawAxis(Constants.kControllerAXIS_LY));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.angler.brakeOn();
  }

  @Override
  protected void interrupted() {
    end();
  }
}