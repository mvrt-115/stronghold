package org.usfirst.frc.team115.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//DriveTrain
	public static final int FRONT_LEFT_MOTOR = 0;
	public static final int FRONT_RIGHT_MOTOR = 1;
	public static final int BACK_LEFT_MOTOR = 2;
	public static final int BACK_RIGHT_MOTOR = 3;
	
	//Joystick ID
	public static final int JOYSTICK = 0;
	
	//DriveStraight
	public static final int ENCODER_SCALE = 1444; //TODO change number if necessary
	public static final double DRIVE_OUTPUT_RANGE = 0.6;
	public static final double DRIVE_SETPOINT = 1; //TODO
	
	//Shooter Angler
	public static final int SHOOTER_ANGLER_LEFT = 1;
	public static final int SHOOTER_ANGLER_RIGHT = 2;
	public static final double SHOOTER_ANGLER_SPEED = 0.5; //TODO
	public static final int ENCODER_ANGLER_A = 1;
	public static final int ENCODER_ANGLER_B = 2;
	public static final double ENCODER_ONE_DEGREE = 1024/360;
	public static final int BUTTON_ANGLE_UP = 1;
	public static final int BUTTON_ANGLE_DOWN = 2;
	public static final double BOTTOM_HEIGHT = 0;
	public static final int LIMIT_SWITCH_TOP = 1;
	public static final int LIMIT_SWITCH_BOTTOM = 2;
	
	//Shooter Intake
	public static final int SHOOTER_INTAKE_TOP = 1;
	public static final int SHOOTER_INTAKE_BOTTOM = 2;
	public static final double SHOOTER_INTAKE_SPEED = 0.5; //TODO 
	
}
