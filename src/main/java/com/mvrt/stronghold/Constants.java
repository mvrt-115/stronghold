package com.mvrt.stronghold;

import com.mvrt.lib.ConstantsBase;

public class Constants extends ConstantsBase {

  public static int kUpperLimitSwitchID = 4;
  public static int kLowerLimitSwitchID = 5;

  public static double kDriveKp = 0.08;
  public static double kDriveKi = 0.001;
  public static double kDriveKd = 0;
  public static double kDrivePIDTolerance = 2.5;

  public static double kDriveDistancePerTick = 8 * Math.PI / 250;
  public static double kDriveDistanceThreshold = 0.05;
  public static double kDrivePrecision = 0.75;

  public static double kDriveDefaultSpeed = 0.75;

  public static double kDriveTurnKp = 0;
  public static double kDriveTurnKi = 0;
  public static double kDriveTurnKd = 0;

  public static double kDriveTurnOnTargetKp = 0.025;
  public static double kDriveTurnOnTargetKi = 0;
  public static double kDriveTurnOnTargetKd = 0.09;

  public static double kFlywheelRotationsPerTick = 1.0 / 4096.0;

  public static double kFlywheelKp = 0.6;
  public static double kFlywheelKi = 0;
  public static double kFlywheelKd = 0.008;
  public static double kFlywheelKpOnTarget = 0.3;
  public static double kFlywheelKiOnTarget = 0;
  public static double kFlywheelKdOnTarget = 0.008;

  public static double kAnglerTopKp = 0.008;
  public static double kAnglerTopKi = 0.00015;
  public static double kAnglerTopKd = 0.007;

  public static double kAnglerMiddleUpKp = 0.017;
  public static double kAnglerMiddleUpKi = 0.00015;
  public static double kAnglerMiddleUpKd = 0.007;

  public static double kAnglerMiddleDownKp = 0.008;
  public static double kAnglerMiddleDownKi = 0;
  public static double kAnglerMiddleDownKd = 0.009;

  public static double kAnglerBottomUpKp = 0.017;
  public static double kAnglerBottomUpKi = 0.00015;
  public static double kAnglerBottomUpKd = 0.007;

  public static double kAnglerBottomDownKp = 0.008;
  public static double kAnglerBottomDownKi = 0;
  public static double kAnglerBottomDownKd = 0.0095;

  public static double kAnglerDegreesPerVolt = 360.0 / 5.0;
  public static double kAnglerVoltsPerDegree = 5.0 / 360.0;

  public static final int kDriveJoystick = 0;
  public static final int kOperatorJoystick = 1;

  public static final double kShooterIntakePreset = -0.3;
  public static final int kShooterSpyBotPreset = 25;
  public static final int kShooterBatterPreset = 11;

  public static int kDriveLeftFrontId = 1;
  public static int kDriveLeftRearId = 2;
  public static int kDriveRightFrontId = 3;
  public static int kDriveRightRearId = 4;

  public static int kAnglerTalonOneId = 8;
  public static int kAnglerTalonTwoId = 7;

  public static int kFlywheelLeftId = 5;
  public static int kFlywheelRightId = 6;

  public static int kPunchSolenoidA = 0;
  public static int kPunchSolenoidB = 1;

  public static int kAnglerEncoder = 5;

  public static int kDriveEncoderLeftA = 8;
  public static int kDriveEncoderLeftB = 9;

  public static int kDriveEncoderRightA = 6;
  public static int kDriveEncoderRightB = 7;

  public static int kFlywheelEncoderLeftA = 2;
  public static int kFlywheelEncoderLeftB = 3;
  public static int kFlywheelEncoderRightA = 0;
  public static int kFlywheelEncoderRightB = 1;

  public static int kAnglerHallEffectsBottomLimit = 10;
  public static int kAnglerHallEffectsBatter = 11;
  public static int kAnglerHallEffectsTopLimit = 12;

  public static double kAnglerBatterPreset = -113;
  public static double kAnglerSpyBotPreset = 65;
  public static double kAnglerTopPreset = 90;
  public static double kAnglerBottomPreset = 0;
  public static double kAnglerLowBarPreset = -17;

  public static double kAnglerError = 3;
  public static double kAnglerTolerance = 0.5;


  public static double[] kAnglerPresets = {kAnglerBatterPreset,
                                           kAnglerSpyBotPreset,
                                           kAnglerBottomPreset};

  public static int kAnglerBrakePortOne = 2;
  public static int kAnglerBrakePortTwo = 3;


  public static int kIntakeTalonId = 9;

  public static int kDriveIntakeButton = 1;

  public static int kShootPresetButton1 = 5;
  public static int kShootPresetButton2 = 6;

  public static int kArduinoBoardAnglerUp = 2;
  public static int kArduinoBoardShoot = 4;
  public static int kArduinoBoardAnglerDown = 5;
  public static int kArduinoBoardFlywheelStart = 1;
  public static int kArduinoBoardFlywheelStop = 3;

  public static int kControllerA = 2;
  public static int kControllerB = 3;
  public static int kControllerY = 4;
  public static int kControllerX = 1;
  public static int kControllerLB = 5;
  public static int kControllerRB = 6;
  public static int kControllerBACK = 9;
  public static int kControllerSTART = 10;
  public static int kControllerLAXIS_PRESS = 11;
  public static int kControllerRAXIS_PRESS = 12;
  public static int kControllerRT = 8;
  public static int kControllerLT = 7;
  public static int kControllerAXIS_LX = 0;
  public static int kControllerAXIS_LY = 1;
  public static int kControllerAXIS_RX = 4;
  public static int kControllerAXIS_RY = 5;
  public static int kControllerD_PAD_UP = 0;
  public static int kControllerD_PAD_DOWN = 4;

  public static int kPcmId = 1;


  public static double kAnglerTimeOut = 5; //Seconds
  public static double kFlywheelBatterPower = 10;

    static {
    new Constants().loadFromFile();
  }

  @Override
  public String getFileLocation() {
    return "~/constants.txt";
  }
}
