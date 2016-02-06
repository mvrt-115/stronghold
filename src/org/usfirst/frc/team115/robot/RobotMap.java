package org.usfirst.frc.team115.robot;

public class RobotMap {
	
	// Motors
	public static final int DRIVE_LEFT_FRONT_MOTOR = 0;
	public static final int DRIVE_RIGHT_FRONT_MOTOR = 1;
	public static final int DRIVE_LEFT_BACK_MOTOR = 2;
	public static final int DRIVE_RIGHT_BACK_MOTOR = 3;
	public static final int SHOOTER_ANGLER_LEFT_MOTOR = 4;
	public static final int SHOOTER_ANGLER_RIGHT_MOTOR = 5;

	// Values
	public static final int ENCODER_SCALE = 1024; //TODO change number if necessary
	public static final double DRIVE_OUTPUT_RANGE = 0.6;
	public static final double DRIVE_SETPOINT = 1; //TODO
	public static final double SHOOTER_ANGLER_SPEED = 0.5; //TODO
	public static final int ENCODER_ANGLER_A = 1;
	public static final int ENCODER_ANGLER_B = 2;
	public static final double ENCODER_ONE_DEGREE = 1024/360;
	public static final int BUTTON_ANGLE_UP = 1;
	public static final int BUTTON_ANGLE_DOWN = 2;
	public static final double BOTTOM_HEIGHT = 0;
	
	//Joystick
	public static final int JOYSTICK = 0;

	//compressor
	public static final int PCM = 1;
	
	//Shooter Angler
	
	
	
	
	public static final int LIMIT_SWITCH_TOP = 1;
	public static final int LIMIT_SWITCH_BOTTOM = 2;
	
	//Shooter Intake
	public static final int SHOOTER_INTAKE_TOP = 1;
	public static final int SHOOTER_INTAKE_BOTTOM = 2;
	public static final double SHOOTER_INTAKE_SPEED = 0.5; //TODO 
	public static final int PUNCH_SOLENOID_A = 1;
	public static final int PUNCH_SOLENOID_B = 2;

	//Winch
	public static final int BRAKE_SOLENOID_A = 1;
	public static final int BRAKE_SOLENOID_B = 2;
	public static final int ENCODERA = 1;
	public static final int ENCODERB = 2;
	public static final int ARM_SOLENOID_A = 3;
	public static final int ARM_SOLENOID_B = 4;
}
