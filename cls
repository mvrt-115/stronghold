[1mdiff --git a/src/main/java/com/mvrt/stronghold/Constants.java b/src/main/java/com/mvrt/stronghold/Constants.java[m
[1mindex d97d019..646683f 100644[m
[1m--- a/src/main/java/com/mvrt/stronghold/Constants.java[m
[1m+++ b/src/main/java/com/mvrt/stronghold/Constants.java[m
[36m@@ -39,6 +39,7 @@[m [mpublic class Constants extends ConstantsBase {[m
 [m
   public static final int kDriveJoystick = 0;[m
   public static final int kOperatorJoystick = 1;[m
[32m+[m[32m  public static final int kgameJoystick = 2;[m
 [m
   public static final int kIntakeButton = 4;[m
   public static final int kShootPresetButton1 = 5;[m
[36m@@ -86,6 +87,29 @@[m [mpublic class Constants extends ConstantsBase {[m
   public static int kAnglerBrakePortOne;[m
   public static int kAnglerBrakePortTwo;[m
 [m
[32m+[m[32m  public static final int JOYSTICK_A = 1;[m
[32m+[m[32m  public static final int JOYSTICK_B = 2;[m
[32m+[m[32m  public static final int JOYSTICK_Y = 4;[m
[32m+[m[32m  public static final int JOYSTICK_X = 3;[m
[32m+[m[32m  public static final int JOYSTICK_LB = 5;[m
[32m+[m[32m  public static final int JOYSTICK_RB = 6;[m
[32m+[m[32m  public static final int JOYSTICK_BACK = 7;[m
[32m+[m[32m  public static final int JOYSTICK_START = 8;[m
[32m+[m[32m  public static final int JOYSTICK_LAXIS_PRESS = 9;[m
[32m+[m[32m  public static final int JOYSTICK_RAXIS_PRESS = 10;[m
[32m+[m[32m  public static final int JOYSTICK_RT = 3;[m
[32m+[m[32m  public static final int JOYSTICK_LT = 2;[m
[32m+[m[32m  public static final int JOYSTICK_AXIS_LX = 0;[m
[32m+[m[32m  public static final int JOYSTICK_AXIS_LY = 1;[m
[32m+[m[32m  public static final int JOYSTICK_AXIS_RX = 4;[m
[32m+[m[32m  public static final int JOYSTICK_AXIS_RY = 5;[m
[32m+[m
[32m+[m[32m  //angler angles[m
[32m+[m[32m  public static final int AngleA = 0; //?[m
[32m+[m[32m  public static final int AngleB = 45;//?[m
[32m+[m[32m  public static final int AngleC = 90; //?[m
[32m+[m[32m  public static final int AngleD = 95; //?[m
[32m+[m
   static {[m
     new Constants().loadFromFile();[m
   }[m
[1mdiff --git a/src/main/java/com/mvrt/stronghold/OperatorInterface.java b/src/main/java/com/mvrt/stronghold/OperatorInterface.java[m
[1mindex 4fa26ad..66bc558 100644[m
[1m--- a/src/main/java/com/mvrt/stronghold/OperatorInterface.java[m
[1m+++ b/src/main/java/com/mvrt/stronghold/OperatorInterface.java[m
[36m@@ -3,6 +3,7 @@[m [mpackage com.mvrt.stronghold;[m
 import com.mvrt.lib.ConstantsBase;[m
 import com.mvrt.stronghold.commands.SetFlywheelSpeed;[m
 import com.mvrt.stronghold.commands.Shoot;[m
[32m+[m[32mimport com.mvrt.stronghold.commands.AnglerMoveToAngle[m
 import edu.wpi.first.wpilibj.Joystick;[m
 import edu.wpi.first.wpilibj.buttons.JoystickButton;[m
 [m
[36m@@ -11,8 +12,10 @@[m [mimport edu.wpi.first.wpilibj.buttons.JoystickButton;[m
  * interface to the commands and command groups that allow control of the robot.[m
  */[m
 public class OperatorInterface {[m
[31m-  Joystick driveJoystick;[m
   Joystick operatorJoystick;[m
[32m+[m[32m  Joystick driveJoystick;[m
[32m+[m[32m  // angler angles[m
[32m+[m[32m  int angle = 0;[m
 [m
   public OperatorInterface() {[m
     driveJoystick = new Joystick(Constants.kDriveJoystick);[m
[36m@@ -23,22 +26,67 @@[m [mpublic class OperatorInterface {[m
         .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterIntake));[m
     new JoystickButton(operatorJoystick, Constants.kIntakeButton)[m
         .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterIntake));[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.JOYSTICK_LT)[m
[32m+[m[32m       .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterIntake));[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.JOYSTICK_LT)[m
[32m+[m[32m            .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterIntake));[m
 [m
     // run one preset of the flywheels[m
     new JoystickButton(operatorJoystick, Constants.kShootPresetButton1)[m
         .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset1));[m
     new JoystickButton(operatorJoystick, Constants.kShootPresetButton1)[m
         .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset1));[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.JOYSTICK_Y)[m
[32m+[m[32m        .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset1));[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.JOYSTICK_Y)[m
[32m+[m[32m        .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset1));[m
 [m
     // run another preset of the flywheels[m
     new JoystickButton(operatorJoystick, Constants.kShootPresetButton2)[m
         .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset2));[m
     new JoystickButton(operatorJoystick, Constants.kShootPresetButton2)[m
         .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset2));[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.JOYSTICK_X)[m
[32m+[m[32m        .whileActive(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kShooterPreset2));[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.JOYSTICK_X)[m
[32m+[m[32m        .whileActive(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kShooterPreset2));[m
 [m
[31m-    // shoot the ball[m
[31m-    new JoystickButton(operatorJoystick, Constants.kExtendPistonButton).whenPressed(new Shoot());[m
[32m+[m[32m    //angler position[m
[32m+[m
[32m+[m[32m      new JoystickButton (operatorJoystick, Constants.JOYSTICK_START)[m
[32m+[m[32m              .whenPressed(angle++);[m
[32m+[m[32m      if (angle == 0){[m
[32m+[m[32m        new AnglerMoveToAngle(Constants.AngleA);[m
[32m+[m[32m      }[m
[32m+[m[32m      if (angle == 1){[m
[32m+[m[32m        new AnglerMoveToAngle(Constants.AngleB);[m
[32m+[m[32m      }[m
[32m+[m[32m      if(angle == 2){[m
[32m+[m[32m        new AnglerMoveToAngle(Constants.AngleC);[m
[32m+[m[32m      }[m
[32m+[m[32m      if (angle == 3){[m
[32m+[m[32m        new AnglerMoveToAngle(Constants.AngleD);[m
[32m+[m[32m      }[m
[32m+[m[32m      if (angle == 4){[m
[32m+[m[32m        angle = 0;[m
[32m+[m[32m      }[m
 [m
[32m+[m
[32m+[m[32m    //auto-aim: no command made yet[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.JOYSTICK_LB);[m
[32m+[m
[32m+[m[32m    //auto-shoot: no command made yet[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.JOYSTICK_RB);[m
[32m+[m
[32m+[m[32m    //aim and shoot: no command made yet[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.JOYSTICK_A);[m
[32m+[m
[32m+[m[32m    //battershot: no command made yet[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.JOYSTICK_B);[m
[32m+[m
[32m+[m[32m      // shoot the ball[m
[32m+[m[32m    new JoystickButton(operatorJoystick, Constants.kExtendPistonButton).whenPressed(new Shoot());[m
[32m+[m[32m    new JoystickButton (operatorJoystick, Constants.JOYSTICK_RT).whenPressed(new Shoot());[m
   }[m
 [m
   public Joystick getDriveJoystick() {[m
[36m@@ -48,5 +96,6 @@[m [mpublic class OperatorInterface {[m
   public Joystick getOperatorJoystick() {[m
     return operatorJoystick;[m
   }[m
[32m+[m
 }[m
 [m
