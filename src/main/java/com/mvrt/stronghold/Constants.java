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

  public static double kAnglerKp = 0;
  public static double kAnglerKi = 0;
  public static double kAnglerKd = 0;

  public static int kEndEditableArea = 0;

  public static final int kDriveJoystick = 0;
  public static final int kOperatorJoystick = 1;

  public static final int kIntakeButton = 4;
  public static final int kShootPresetButton1 = 2;
  public static final int kShootPresetButton2 = 3;
  public static final int kExtendPistonButton = 1;
  public static final int kIntakePresetButton = 11;
  public static final int kBatterPresetButton = 9;

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

  public static int kIntakePreset = 0;
  public static int kBatterPreset = 113;

  static {
    new Constants().loadFromFile();
  }

  @Override
  public String getFileLocation() {
    return "~/constants.txt";
  }
}
