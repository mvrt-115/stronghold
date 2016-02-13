package org.usfirst.frc.team115.robot;

import edu.wpi.first.wpilibj.DigitalSource;

public class RobotMap {
	
	// Motors
	public static final int DRIVE_LEFT_FRONT_MOTOR = 1;
	public static final int DRIVE_RIGHT_FRONT_MOTOR = 4;
	public static final int DRIVE_LEFT_BACK_MOTOR = 2;
	public static final int DRIVE_RIGHT_BACK_MOTOR = 3;
	public static final int ANGLER_LEFT_MOTOR = 4;
	public static final int ANGLER_RIGHT_MOTOR = 5;
	public static final int FLYWHEEL_LEFT_MOTOR = 6;
	public static final int FLYWHEEL_RIGHT_MOTOR = 7;
	public static final int WINCH_LEFT_MOTOR = 8;
	public static final int WINCH_RIGHT_MOTOR = 9;
	
	// Joystick
	public static final int JOYSTICK = 0;

	// Compressor
	public static final int PCM = 1;
	
	// Solenoids
	public static final int PUNCH_SOLENOID_A = 1;
	public static final int PUNCH_SOLENOID_B = 2;
	public static final int BRAKE_SOLENOID_A = 3;
	public static final int BRAKE_SOLENOID_B = 4;
	public static final int ARM_SOLENOID_A = 5;
	public static final int ARM_SOLENOID_B = 6;
	
	// Hall Effects
	public static final int ANGLER_BOTTOM_HALL = 0;
	public static final int ANGLER_SHOOT_HALL = 1;
	public static final int ANGLER_TOP_HALL = 2;
	
	// Encoders
  public static final int FLYWHEEL_LEFT_ENCODER_A = 0;
  public static final int FLYWHEEL_LEFT_ENCODER_B = 1;
  public static final int FLYWHEEL_RIGHT_ENCODER_A = 2;
  public static final int FLYWHEEL_RIGHT_ENCODER_B = 3;
}
