package org.usfirst.frc.team115.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//DriveTrain
	public static final int LEFT_MOTOR = 0;
	public static final int RIGHT_MOTOR = 1;
	public static final int LEFT_BACK_MOTOR = 2;
	public static final int RIGHT_BACK_MOTOR = 3;
	
	
	//Joystick ID
	public static final int JOYSTICK = 0;
	
	//DriveStraight
	public static final int ENCODER_SCALE = 1444; //TODO change number if necessary
	public static final double DRIVE_OUTPUT_RANGE = 0.6;
	public static final double DRIVE_SETPOINT = 1; //TODO
	

	
}
