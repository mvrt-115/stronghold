package org.usfirst.frc.team115.robot;

import edu.wpi.first.wpilibj.DigitalSource;

public class RobotMap {
	
  // Joystick
  public static final int JOYSTICK = 0;
  
  //JoystickButton
  public static final int BUTTON_DRIVE_STRAIGHT = 1;
  public static final int BUTTON_DRIVE_PRECISION = 2;
  
  // Motors
  public static final int ANGLER_MOTOR_LEFT = 1;
  public static final int ANGLER_MOTOR_RIGHT = 2;
  public static final int DRIVE_MOTOR_LEFT_FRONT = 1;
  public static final int DRIVE_MOTOR_RIGHT_FRONT = 3;
  public static final int DRIVE_MOTOR_LEFT_BACK = 2;
  public static final int DRIVE_MOTOR_RIGHT_BACK = 4;
  public static final int FLYWHEEL_MOTOR_LEFT = 7;
  public static final int FLYWHEEL_MOTOR_RIGHT = 8;
  public static final int WINCH_MOTOR_LEFT = 9;
  public static final int WINCH_MOTOR_RIGHT = 10;
  
  // Compressor
  public static final int PCM = 1;
  
  // Solenoids
  public static final int ARM_SOLENOID_A = 5;
  public static final int ARM_SOLENOID_B = 6;
  public static final int BRAKE_SOLENOID_A = 3;
  public static final int BRAKE_SOLENOID_B = 4;
  public static final int PUNCH_SOLENOID_A = 1;
  public static final int PUNCH_SOLENOID_B = 2;
  
  // Hall Effects
  public static final int ANGLER_HALL_BOTTOM = 0;
  public static final int ANGLER_HALL_MIDDLE = 1;
  public static final int ANGLER_HALL_TOP = 2;
  
  // Encoders
  public static final int ANGLER_ENCODER_LEFT_A = 1;
  public static final int ANGLER_ENCODER_LEFT_B = 1;
  public static final int ANGLER_ENCODER_RIGHT_A = 2;
  public static final int ANGLER_ENCODER_RIGHT_B = 2;
  public static final int DRIVE_ENCODER_LEFT_A = 3;
  public static final int DRIVE_ENCODER_LEFT_B = 3;
  public static final int DRIVE_ENCODER_RIGHT_A = 4;
  public static final int DRIVE_ENCODER_RIGHT_B = 4;
  public static final int FLYWHEEL_ENCODER_LEFT_A = 5;
  public static final int FLYWHEEL_ENCODER_LEFT_B = 5;
  public static final int FLYWHEEL_ENCODER_RIGHT_A = 6;
  public static final int FLYWHEEL_ENCODER_RIGHT_B = 6;
  
}
