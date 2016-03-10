package com.mvrt.stronghold;

import com.mvrt.lib.ConstantsBase;
import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import com.mvrt.stronghold.commands.Shoot;
import com.mvrt.stronghold.commands.AnglerMoveToAngle
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OperatorInterface {
  Joystick operatorJoystick;
  Joystick driveJoystick;
  // angler angles
  int angle = 0;

  public OperatorInterface() {
    driveJoystick = new Joystick(Constants.kDriveJoystick);
    operatorJoystick = new Joystick(Constants.kOperatorJoystick);

    // intake with the flywheels
    new JoystickButton(operatorJoystick, Constants.kIntakeButton)
        .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterIntake));
    new JoystickButton(operatorJoystick, Constants.kIntakeButton)
        .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterIntake));
    new JoystickButton(operatorJoystick, Constants.JOYSTICK_LT)
       .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterIntake));
    new JoystickButton(operatorJoystick, Constants.JOYSTICK_LT)
            .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterIntake));

    // run one preset of the flywheels
    new JoystickButton(operatorJoystick, Constants.kShootPresetButton1)
        .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset1));
    new JoystickButton(operatorJoystick, Constants.kShootPresetButton1)
        .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset1));
    new JoystickButton(operatorJoystick, Constants.JOYSTICK_Y)
        .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset1));
    new JoystickButton(operatorJoystick, Constants.JOYSTICK_Y)
        .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset1));

    // run another preset of the flywheels
    new JoystickButton(operatorJoystick, Constants.kShootPresetButton2)
        .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset2));
    new JoystickButton(operatorJoystick, Constants.kShootPresetButton2)
        .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset2));
    new JoystickButton(operatorJoystick, Constants.JOYSTICK_X)
        .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset2));
    new JoystickButton(operatorJoystick, Constants.JOYSTICK_X)
        .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset2));

    //angler position

      new JoystickButton (operatorJoystick, Constants.JOYSTICK_START)
              .whenPressed(angle++);
      if (angle == 0){
        new AnglerMoveToAngle(Constants.AngleA);
      }
      if (angle == 1){
        new AnglerMoveToAngle(Constants.AngleB);
      }
      if(angle == 2){
        new AnglerMoveToAngle(Constants.AngleC);
      }
      if (angle == 3){
        new AnglerMoveToAngle(Constants.AngleD);
      }
      if (angle == 4){
        angle = 0;
      }


    //auto-aim: no command made yet
    new JoystickButton(operatorJoystick, Constants.JOYSTICK_LB);

    //auto-shoot: no command made yet
    new JoystickButton(operatorJoystick, Constants.JOYSTICK_RB);

    //aim and shoot: no command made yet
    new JoystickButton(operatorJoystick, Constants.JOYSTICK_A);

    //battershot: no command made yet
    new JoystickButton(operatorJoystick, Constants.JOYSTICK_B);
      // shoot the ball
    new JoystickButton(operatorJoystick, Constants.kExtendPistonButton).whenPressed(new Shoot());
    new JoystickButton (operatorJoystick, Constants.JOYSTICK_RT).whenPressed(new Shoot());
  }

  public Joystick getDriveJoystick() {
    return driveJoystick;
  }

  public Joystick getOperatorJoystick() {
    return operatorJoystick;
  }

}

