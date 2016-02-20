package com.mvrt.stronghold;

import com.mvrt.lib.ConstantsBase;

public class Constants extends ConstantsBase {

  public static double kDriveDistancePerTick = 8 * Math.PI / 250;

  public static double kAnglerDegreesPerVolt = 360.0 / 5.0;
  public static double kAnglerVoltsPerDegree = 5.0 / 360.0;

  public static double kFlywheelRotationsPerTick = 1.0 / 4096.0;

  public static double kFlywheelKp = 0.8;
  public static double kFlywheelKi = 0;
  public static double kFlywheelKd = 0.008;

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

  public static int kEndEditableArea = 0;

  public static final int kDriveJoystick = 0;
  public static final int kOperatorJoystick = 1;

  public static final int kIntakeButton = 4;
  public static final int kShootPresetButton1 = 5;
  public static final int kShootPresetButton2 = 6;
  public static final int kExtendPistonButton = 1;

  public static final double kShooterIntake = -4;
  public static final double kShooterPreset1 = 20;
  public static final double kShooterPreset2 = 10;

  public static int kDriveLeftFrontId = 1;
  public static int kDriveLeftRearId = 2;
  public static int kDriveRightFrontId = 3;
  public static int kDriveRightRearId = 4;

  public static int kPcmId = 1;

  public static int kAnglerTalonOneId = 7;
  public static int kAnglerTalonTwo = 8;

  public static int kFlywheelLeftId = 5;
  public static int kFlywheelRightId = 6;

  public static int kArmSolenoidA = 5;
  public static int kArmSolenoidB = 6;

  public static int kPunchSolenoidA = 0;
  public static int kPunchSolenoidB = 1;

  public static int kAnglerEncoder = 0;

  public static int kDriveEncoderLeftA = 6;
  public static int kDriveEncoderLeftB = 7;

  public static int kDriveEncoderRightA = 2;
  public static int kDriveEncoderRightB = 3;

  public static int kFlywheelEncoderLeftA = 4;
  public static int kFlywheelEncoderLeftB = 5;
  public static int kFlywheelEncoderRightA = 0;
  public static int kFlywheelEncoderRightB = 1;

  public static int kAnglerHallEffectsBottomLimit = 10;
  public static int kAnglerHallEffectsBatter = 11;
  public static int kAnglerHallEffectsTopLimit = 12;

  public static int kAnglerBrakePortOne = 2;
  public static int kAnglerBrakePortTwo = 3;


  static {
    new Constants().loadFromFile();
  }

  @Override
  public String getFileLocation() {
    return "~/constants.txt";
  }
}
