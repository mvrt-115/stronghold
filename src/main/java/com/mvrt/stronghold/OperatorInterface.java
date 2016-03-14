package com.mvrt.stronghold;

import com.mvrt.lib.ConstantsBase;
import com.mvrt.stronghold.commands.AnglerMoveToAngle;
import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import com.mvrt.stronghold.commands.Shoot;
import com.mvrt.stronghold.indep.BatterShot;
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
    // Batter shoot button
    new JoystickButton(operatorJoystick, Constants.kOperatorBatterButton)
            .whenPressed(new BatterShot());

    // Angler batter preset
    new JoystickButton(operatorJoystick, Constants.kOperatorBatterPresetButton)
            .whenPressed(new AnglerMoveToAngle(Constants.kAnglerBatterPreset));

    // Angler Low Bar Preset
    new JoystickButton(operatorJoystick, Constants.kOperatorLowBarPresetButton)
            .whenPressed(new AnglerMoveToAngle(Constants.kAnglerLowBarPreset));
  }

  public Joystick getDriveJoystick() {
    return driveJoystick;
  }

  public Joystick getOperatorJoystick() {
    return operatorJoystick;
  }
}
