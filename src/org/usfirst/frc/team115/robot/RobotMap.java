package org.usfirst.frc.team115.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	
	//Joystick ID
	public static final int JOYSTICK = 0;
	
	//Shooter Intake
	public static final int SHOOTER_INTAKE_TOP = 1;
	public static final int SHOOTER_INTAKE_BOTTOM = 2;
	public static final double SHOOTER_INTAKE_SPEED = 0.5; //TODO 
	
	//Shooter Encoders
	public static final int ENCODER_ID_A = 0;
	public static final int ENCODER_ID_B = 1;
	public static final int ENCODER_SHOT_ONCE = 1500;
	
}
