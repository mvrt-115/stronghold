package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command {

  public Climb() {
    requires(Robot.climber);
  }

  protected void initialize() {

  }

  protected void execute() {
    Robot.climber.winchUp();
  }

  protected boolean isFinished() {
    return !(Robot.operatorInterface.getOperatorJoystick().getRawButton(Constants.kClimbButton));
  }

  protected void end() {
    Robot.climber.stop();
  }

  protected void interrupted() {
    end();
  }

}
