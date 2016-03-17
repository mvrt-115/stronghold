package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Ishan on 3/14/2016.
 */
public class AnglerMoveWithJoystick extends Command {

  public AnglerMoveWithJoystick(){
    requires(Robot.angler);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.angler.brakeOff();
    Robot.angler.setOutput(Robot.operatorInterface.getDriveJoystick().getZ());
  }

  @Override
  protected boolean isFinished() {
    return Robot.operatorInterface.getDriveJoystick().getRawButton(6);
  }

  @Override
  protected void end() {
    Robot.angler.stop();
  }

  @Override
  protected void interrupted() {

  }
}
