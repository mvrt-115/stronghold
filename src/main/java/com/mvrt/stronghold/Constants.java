package com.mvrt.stronghold;

import com.mvrt.lib.ConstantsBase;

public class Constants extends ConstantsBase {

  public static double kDriveDistancePerTick = 8 * Math.PI / 250;

  public static double kAnglerDegreesPerVolt = 360.0 / 5.0;
  public static double kAnglerVoltsPerDegree = 5.0 / 360.0;

  public static double kFlywheelRotationsPerTick = 1.0 / 1024.0;

  public static double kFlywheelKp = 0;
  public static double kFlywheelKi = 0;
  public static double kFlywheelKd = 0;

  public static double kAnglerBottomUpKp = 0;
  public static double kAnglerBottomUpKi = 0;
  public static double kAnglerBottomUpKd = 0;

  public static double kAnglerBottomDownKp = 0;
  public static double kAnglerBottomDownKi = 0;
  public static double kAnglerBottomDownKd = 0;

  public static double kAnglerMiddleUpKp = 0;
  public static double kAnglerMiddleUpKi = 0;
  public static double kAnglerMiddleUpKd = 0;

  public static double kAnglerMiddleDownKp = 0;
  public static double kAnglerMiddleDownKi = 0;
  public static double kAnglerMiddleDownKd = 0;

  public static double kAnglerTopKp = 0;
  public static double kAnglerTopKi = 0;
  public static double kAnglerTopKd = 0;

  public static int kEndEditableArea = 0;

  public static final int kDriveJoystick = 0;
  public static final int kOperatorJoystick = 1;

  public static final int kIntakeButton = 4;
  public static final int kShootPresetButton1 = 5;
  public static final int kShootPresetButton2 = 6;
  public static final int kExtendPistonButton = 1;

  public static final int kShooterIntake = -50;
  public static final int kShooterPreset1 = 100;
  public static final int kShooterPreset2 = 50;

  public static int kDriveLeftFrontId = 1;
  public static int kDriveLeftRearId = 2;
  public static int kDriveRightFrontId = 3;
  public static int kDriveRightRearId = 4;

  public static int kAnglerTalonOneId = 5;
  public static int kAnglerTalonTwo = 6;

  public static int kFlywheelLeftId = 7;
  public static int kFlywheelRightId = 8;

  public static int kArmSolenoidA = 5;
  public static int kArmSolenoidB = 6;

  public static int kPunchSolenoidA = 0;
  public static int kPunchSolenoidB = 1;

  public static int kAnglerEncoder = 0;

  public static int kDriveEncoderLeftA = 0;
  public static int kDriveEncoderLeftB = 1;

  public static int kDriveEncoderRightA = 2;
  public static int kDriveEncoderRightB = 3;

  public static int kFlywheelEncoderLeftA = 4;
  public static int kFlywheelEncoderLeftB = 5;
  public static int kFlywheelEncoderRightA = 6;
  public static int kFlywheelEncoderRightB = 7;

  public static int kAnglerHallEffectsBottomLimit = 10;
  public static int kAnglerHallEffectsBatter = 11;
  public static int kAnglerHallEffectsTopLimit = 12;

  public static int kAnglerBrakePortOne;
  public static int kAnglerBrakePortTwo;

  public static final int kJoystickA = 2;
  public static final int kJoystickB = 3;
  public static final int kJoystickY = 4;
  public static final int kJoystickX = 1;
  public static final int kJoystickLB = 5;
  public static final int kJoystickRB = 6;
  public static final int kJoystickBACK = 9;
  public static final int kJoystickSTART = 10;
  public static final int kJoystickLAXIS_PRESS = 11;
  public static final int kJoystickRAXIS_PRESS = 12;
  public static final int kJoystickRT = 8;
  public static final int kJoystickLT = 7;
  public static final int kJoystickAXIS_LX = 0;
  public static final int kJoystickAXIS_LY = 1;
  public static final int kJoystickAXIS_RX = 4;
  public static final int kJoystickAXIS_RY = 5;
  public static final int kControllerD_PAD_UP = 0;
  public static final int kControllerD_PAD_DOWN = 4;

  //angler angles
  public static final int AngleA = 0; //?
  public static final int AngleB = 45;//?
  public static final int AngleC = 90; //?
  public static final int AngleD = 95; //?

  static {
    new Constants().loadFromFile();
  }

  @Override
  public String getFileLocation() {
    return "~/constants.txt";
  }
}
