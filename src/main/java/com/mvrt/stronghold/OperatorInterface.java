package com.mvrt.stronghold;

import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OperatorInterface {
  Joystick driveJoystick;

  public OperatorInterface() {
    driveJoystick = new Joystick(Constants.kDriveJoystick);

    // TODO(achierius) add operator joystick and mapping

    // stop the flywheels
    new JoystickButton(driveJoystick, 4).whenPressed(new SetFlywheelSpeed(Robot.leftFlywheel, 0));
    new JoystickButton(driveJoystick, 4).whenPressed(new SetFlywheelSpeed(Robot.rightFlywheel, 0));

    // run one preset of the flywheels
    new JoystickButton(driveJoystick, 5).whenPressed(new SetFlywheelSpeed(Robot.leftFlywheel, 100));
    new JoystickButton(driveJoystick, 5)
        .whenPressed(new SetFlywheelSpeed(Robot.rightFlywheel, 100));
  }

  public Joystick getDriveJoystick() {
    return driveJoystick;
  }
}

