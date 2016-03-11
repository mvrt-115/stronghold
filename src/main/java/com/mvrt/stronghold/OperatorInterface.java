package com.mvrt.stronghold;

import com.mvrt.lib.ConstantsBase;
import com.mvrt.stronghold.commands.AnglerMoveToAngle;
import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import com.mvrt.stronghold.commands.Shoot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OperatorInterface {
  Joystick driveJoystick;
  Joystick operatorJoystick;
  private int counter;
  // make int to use as counter as the index of the preset

  public OperatorInterface() {
    driveJoystick = new Joystick(Constants.kDriveJoystick);
    operatorJoystick = new Joystick(Constants.kOperatorJoystick);
    counter = 0;

    // intake with the flywheels
    new JoystickButton(operatorJoystick, Constants.kIntakeButton)
        .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterIntake));
    new JoystickButton(operatorJoystick, Constants.kIntakeButton)
        .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterIntake));

    // run one preset of the flywheels
    new JoystickButton(operatorJoystick, Constants.kShootPresetButton1)
        .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset1));
    new JoystickButton(operatorJoystick, Constants.kShootPresetButton1)
        .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset1));

    // run another preset of the flywheels
    new JoystickButton(operatorJoystick, Constants.kShootPresetButton2)
        .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset2));
    new JoystickButton(operatorJoystick, Constants.kShootPresetButton2)
        .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset2));

    // all buttons possible on gamepad.

    new JoystickButton(operatorJoystick, Constants.kControllerA)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerB)
        .whileActive(null); //Cancel All Auto Mode

    new JoystickButton(operatorJoystick, Constants.kControllerX)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerY)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerLB)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerRB)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerBACK)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerSTART)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerLAXIS_PRESS)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerRAXIS_PRESS)
        .whileActive(null);

    new JoystickTrigger(operatorJoystick, Constants.kControllerRT, 0.6)
        .whileActive(null); // IntakeOpen

    new JoystickTrigger(operatorJoystick, Constants.kControllerLT, 0.6)
        .whileActive(null); // IntakeClose

    new JoystickButton(operatorJoystick, Constants.kControllerAXIS_LX)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerAXIS_LY)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerAXIS_RX)
        .whileActive(null);

    new JoystickButton(operatorJoystick, Constants.kControllerAXIS_RY)
        .whileActive(null);

    //POV Hat(D-PAD) on GamePad
    new PovTrigger(operatorJoystick, Constants.kControllerD_PAD_UP)
        .whileActive(setPresetAngle(1));


    new PovTrigger(operatorJoystick, Constants.kControllerD_PAD_DOWN)
        .whileActive(setPresetAngle(-1));


    // run preset

    // shoot the ball
    new JoystickButton(operatorJoystick, Constants.kExtendPistonButton).whenPressed(new Shoot());

  }

  public AnglerMoveToAngle setPresetAngle(int count) {
    int value = counter + count;
    if ((value > -1) && (value < Constants.kAnglerPresets.length)) {
      counter = value;
    }
    return (new AnglerMoveToAngle(Constants.kAnglerPresets[counter]));
  }

  public Joystick getDriveJoystick() {
    return driveJoystick;
  }

  public Joystick getOperatorJoystick() {
    return operatorJoystick;
  }



  class JoystickTrigger extends Trigger {
    private Joystick operatorJoystick;
    private int channel;
    private double threshold;

    public JoystickTrigger(Joystick joystick, int channel, double threshold) {
      this.operatorJoystick = joystick;
      this.channel = channel;
      this.threshold = threshold;
    }

    @Override
    public boolean get() {
      return operatorJoystick.getRawAxis(channel) >= threshold;
    }
  }

  class PovTrigger extends Trigger {
    private Joystick operatorJoystick;
    private int angle;

    public PovTrigger(Joystick joystick, int angle) {
      this.operatorJoystick = joystick;
      this.angle = angle;
    }

    @Override
    public boolean get() {
      return operatorJoystick.getPOV() == angle;
    }
  }
}

