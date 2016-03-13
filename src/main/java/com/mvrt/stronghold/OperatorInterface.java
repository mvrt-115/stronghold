package com.mvrt.stronghold;

import com.mvrt.stronghold.commands.AnglerMoveToAngle;
import com.mvrt.stronghold.commands.Fire;
import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import com.mvrt.stronghold.commands.Shoot;

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
    new JoystickButton(operatorJoystick, Constants.kIntakeButton)
        .whenPressed(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterIntake));
    new JoystickButton(operatorJoystick, Constants.kIntakeButton)
        .whenPressed(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterIntake));

    // run one preset of the flywheels
    /*new JoystickButton(operatorJoystick, Constants.kShootPresetButton1)
        .whenPressed(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset1));
    new JoystickButton(operatorJoystick, Constants.kShootPresetButton1)
        .whenPressed(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset1));*/ //DEPRECATED

    // run another preset of the flywheels
    /*new JoystickButton(operatorJoystick, Constants.kShootPresetButton2)
        .whenPressed(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset2));
    new JoystickButton(operatorJoystick, Constants.kShootPresetButton2)
        .whenPressed(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset2));*/ //DEPRECATED
    

    // shoot the ball
    new JoystickButton(operatorJoystick, Constants.kExtendPistonButton).whenPressed(new Fire());
  }
  
  /*public double getAnglerPreset() {
    double location = operatorJoystick.getThrottle(); // TODO get the correct axis
    return Constants.kAnglerPresets[(int)(location * (Constants.kAnglerPresets.length - 1))];
  }*/ //DEPRECATED

  public Joystick getDriveJoystick() {
    return driveJoystick;
  }

  public Joystick getOperatorJoystick() {
    return operatorJoystick;
  }
}

