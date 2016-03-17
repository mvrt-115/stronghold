package com.mvrt.stronghold;

import com.mvrt.stronghold.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OperatorInterface {
  Joystick driveJoystick;
  Joystick operatorJoystick;

  public OperatorInterface() {
    driveJoystick = new Joystick(Constants.kDriveJoystick);
    operatorJoystick = new Joystick(Constants.kOperatorJoystick);

    // intake with the flywheels
    new JoystickButton(driveJoystick, Constants.kIntakeButton)
        .whenPressed(new Intake());
    new JoystickButton(driveJoystick, Constants.kIntakeButton)
        .whenPressed(new Intake());

    new JoystickButton(driveJoystick, Constants.kShootBatterButton)
        .whenPressed(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterBatterPreset));
    new JoystickButton(driveJoystick, Constants.kShootBatterButton)
        .whenPressed(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterBatterPreset));

    new JoystickButton(driveJoystick, Constants.kShootSpyBotButton)
        .whenPressed(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterSpyBotPreset));
    new JoystickButton(driveJoystick, Constants.kShootSpyBotButton)
        .whenPressed(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterSpyBotPreset));

    // shoot the ball
    new JoystickButton(driveJoystick, Constants.kExtendPistonButton).whenPressed(new Shoot());
    new JoystickButton(driveJoystick, 3).whenPressed(new DriveStraight(true));
    new JoystickButton(driveJoystick, 2).whenPressed(new AnglerMoveWithJoystick());
  }

  public Joystick getDriveJoystick() {
    return driveJoystick;
  }

  public Joystick getOperatorJoystick() {
    return operatorJoystick;
  }
}

