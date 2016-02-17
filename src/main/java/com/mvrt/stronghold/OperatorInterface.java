package com.mvrt.stronghold;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OperatorInterface {
  Joystick driveJoystick;

  public OperatorInterface() {
    driveJoystick = new Joystick(Constants.kDriveJoystick);
  }

  public Joystick getDriveJoystick() {
    return driveJoystick;
  }
}

