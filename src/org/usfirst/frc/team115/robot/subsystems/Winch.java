package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * 
 * @author Rithvik Chuppala, Ben Cuan, and Heather Baker
 */


public class Winch extends Subsystem {
	
	private CANTalon[] motors = new CANTalon[2];
	private final int LEFT = 0;
	private final int RIGHT = 1;
  
	private RobotDrive winch;
	
	private Encoder encoderLeft;
	private Encoder encoderRight;
	
	private DoubleSolenoid armSolenoid, brakeSolenoid;
	
	private final double TICKS_PER_INCH = 1.00; //TODO
	
	public Winch() {
		motors[LEFT] = new CANTalon(LEFT);
		motors[RIGHT] = new CANTalon(RIGHT);
		winch = new RobotDrive(motors[LEFT], motors[RIGHT]);
		
		encoderLeft = new Encoder(RobotMap.ENCODER_WINCH_LEFT_A, RobotMap.ENCODER_WINCH_LEFT_B, false, Encoder.EncodingType.k4X);
		encoderRight = new Encoder(RobotMap.ENCODER_WINCH_RIGHT_A, RobotMap.ENCODER_WINCH_RIGHT_B, false, Encoder.EncodingType.k4X);
		
		armSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.ARM_SOLENOID_A, RobotMap.ARM_SOLENOID_B);
		brakeSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.BRAKE_SOLENOID_A, RobotMap.BRAKE_SOLENOID_B);
	}

	public void driveWinch(double move) {
		winch.arcadeDrive(move,0);
	}
	
	public void brake() {
		brakeSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void releaseBrake() {
		brakeSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void liftArm() {
		armSolenoid.set(DoubleSolenoid.Value.kForward); //This line doesn't make sense
	}

	public void stop() {
		brake();
		driveWinch(0);
	}
	
	public double getDistance() {
	  return ((encoderLeft.getDistance() + encoderRight.getDistance()) / (2 * TICKS_PER_INCH));
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
