package com.mvrt.stronghold;

import com.mvrt.lib.ConstantsBase;
import com.mvrt.stronghold.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OperatorInterface {
  private Joystick driveJoystick;
  private Joystick operatorJoystick;

  public OperatorInterface() {
    driveJoystick = new Joystick(Constants.kDriveJoystick);
    operatorJoystick = new Joystick(Constants.kOperatorJoystick);
    initDrive();
    initOperator();
  }

  public void initDrive() {
    new JoystickButton(driveJoystick, Constants.kDriveIntakeButton)
            .whenPressed(new Intake());
  }

  public void initOperator() {
    /*new JoystickButton(operatorJoystick, Constants.kControllerB)
            .whenPressed(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterBatterPreset));
    new JoystickButton(operatorJoystick, Constants.kControllerB)
            .whenPressed(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterBatterPreset));

    new JoystickButton(operatorJoystick, Constants.kControllerA)
            .whenPressed(new Shoot()); */

    new JoystickButton(operatorJoystick, Constants.kArduinoBoardAnglerUp)
            .whenPressed(new AnglerMoveToAngle(100)); //TODO: find actual angle

    new JoystickButton(operatorJoystick, Constants.kArduinoBoardAnglerDown)
            .whenPressed(new LowBar());

    new JoystickButton(operatorJoystick, Constants.kArduinoBoardShoot)
            .whenPressed(new Fire());

   // new JoystickButton(operatorJoystick, Constants.kControllerLAXIS_PRESS)
   //         .toggleWhenPressed(new AnglerMoveWithJoystick());
   // No we're not doing this

   // new JoystickButton(operatorJoystick, Constants.kControllerX)
   //         .whenPressed(new ToggleBrake());
  }

  public Joystick getDriveJoystick() {
    return driveJoystick;
  }

  public Joystick getOperatorJoystick() {
    return operatorJoystick;
  }
}
