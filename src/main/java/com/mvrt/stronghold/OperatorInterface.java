package com.mvrt.stronghold;

import com.mvrt.stronghold.commands.AnglerMoveToAngle;
import com.mvrt.stronghold.commands.MoveToAngle;
import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import com.mvrt.stronghold.commands.Shoot;
import com.mvrt.stronghold.commands.ToggleBrake;
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
            .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kFlywheelIntakeSpeed));
    new JoystickButton(driveJoystick, Constants.kDriveIntakeButton)
            .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kFlywheelIntakeSpeed));
  }

  public void initOperator() {
    new JoystickButton(operatorJoystick, Constants.kControllerB)
            .whenPressed(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterBatterPreset));
    new JoystickButton(operatorJoystick, Constants.kControllerB)
            .whenPressed(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterBatterPreset));

    new JoystickButton(operatorJoystick, Constants.kControllerA)
            .whenPressed(new Shoot());

    new JoystickButton(operatorJoystick, Constants.kControllerY)
            .whenPressed(new AnglerMoveToAngle(Constants.kAnglerLowBarPreset));

    new JoystickButton(operatorJoystick, Constants.kControllerB)
            .whenPressed(new AnglerMoveToAngle(Constants.kAnglerBottomPreset));

    new JoystickButton(operatorJoystick, Constants.kControllerLAXIS_PRESS)
            .toggleWhenPressed(new MoveToAngle());

    new JoystickButton(operatorJoystick, Constants.kControllerX)
            .whenPressed(new ToggleBrake());

  }

  public Joystick getDriveJoystick() {
    return driveJoystick;
  }

  public Joystick getOperatorJoystick() {
    return operatorJoystick;
  }
}
