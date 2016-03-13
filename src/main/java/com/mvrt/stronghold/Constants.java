package com.mvrt.stronghold;

import com.mvrt.lib.ConstantsBase;

public class Constants extends ConstantsBase {

  public static double kDriveDistancePerTick = 8 * Math.PI / 250;

  public static double kDriveKp = 0;
  public static double kDriveKi = 0;
  public static double kDriveKd = 0;

  public static double kDriveDefaultSpeed = 0.75;

  public static double kDriveDistanceThreshold = 0.05;
  public static double kDrivePrecision = 0.75;

  public static double kDriveTurnKp = 0;
  public static double kDriveTurnKi = 0;
  public static double kDriveTurnKd = 0;

  public static double kDriveTurnOnTargetKp = 0.01;
  public static double kDriveTurnOnTargetKi = 0.001;
  public static double kDriveTurnOnTargetKd = 0;

  public static double kAnglerDegreesPerVolt = 360.0 / 5.0;
  public static double kAnglerVoltsPerDegree = 5.0 / 360.0;

  public static double kFlywheelRotationsPerTick = 1.0 / 1024.0;

  public static double kFlywheelKp = 0;
  public static double kFlywheelKi = 0;
  public static double kFlywheelKd = 0;

  public static double kAnglerTopKp = 0.008;
  public static double kAnglerTopKi = 0.00015;
  public static double kAnglerTopKd = 0.007;

  public static double kAnglerMiddleUpKp = 0.015;
  public static double kAnglerMiddleUpKi = 0.00015;
  public static double kAnglerMiddleUpKd = 0.007;

  public static double kAnglerMiddleDownKp = 0.008;
  public static double kAnglerMiddleDownKi = 0;
  public static double kAnglerMiddleDownKd = 0.009;

  public static double kAnglerBottomUpKp = 0.008;
  public static double kAnglerBottomUpKi = 0.00015;
  public static double kAnglerBottomUpKd = 0.007;

  public static double kAnglerBottomDownKp = 0.006;
  public static double kAnglerBottomDownKi = 0;
  public static double kAnglerBottomDownKd = 0.0095;


  public static int kEndEditableArea = 0;
  
  public static final int kPcmId = 1;

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

  public static int kAnglerTalonOneId = 6;
  public static int kAnglerTalonTwo = 5;

  public static int kFlywheelLeftId = 7;
  public static int kFlywheelRightId = 8;

  public static int kLeftArmSolenoidA = 5;
  public static int kLeftArmSolenoidB = 6;
  public static int kRightArmSolenoidA = 7;
  public static int kRightArmSolenoidB = 8;

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

  public static double kAnglerBatterPreset = 45;
  public static double kAnglerTopPreset = 90;
  public static double kAnglerBottomPreset = 0;

  public static double kAnglerError = 3;
  public static double kAnglerTolerance = 1;


  public static double[] kAnglerPresets = {kAnglerBatterPreset,
                                           kAnglerTopPreset,
                                           kAnglerBottomPreset};

  public static int kAnglerBrakePortOne = 2;
  public static int kAnglerBrakePortTwo = 3;
  
  public static int kClimberBrakeA;
  public static int kClimberBrakeB;
  
  public static int kClimberMotor1 = 9;
  public static int kClimberMotor2 = 10;

  static {
    new Constants().loadFromFile();
  }

  @Override
  public String getFileLocation() {
    return "~/constants.txt";
  }
}
