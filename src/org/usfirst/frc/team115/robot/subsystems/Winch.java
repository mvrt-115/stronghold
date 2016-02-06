package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.Robot;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * 
 * @author Rithvik Chuppala and Heather Baker
 */


public class Winch extends Subsystem {
	private CANTalon[] motors = new CANTalon[2];
	private final int LEFT = 0;
	private final int RIGHT = 1;
	
	private DoubleSolenoid armSolenoid, brakeSolenoid;
	
	RobotDrive winch;
	
	public Winch() {
		motors[LEFT] = new CANTalon(LEFT);
		motors[RIGHT] = new CANTalon(RIGHT);
		RobotDrive winch = new RobotDrive(motors[LEFT], motors[RIGHT]);
		
		armSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.ARM_SOLENOID_A, RobotMap.ARM_SOLENOID_B);
		brakeSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.BRAKE_SOLENOID_A, RobotMap.BRAKE_SOLENOID_B);
	}

	public void driveWinch(double move) {
		winch.arcadeDrive(move,0);
	}

	public void raiseRobot() {
		brakeSolenoid.set(DoubleSolenoid.Value.kReverse);
		driveWinch(0.5);
	}

	public void outRope() {
		driveWinch(-0.5);
	}
	
	public void brake() {
		brakeSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void releaseBrake() {
		brakeSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void liftArm() {
		armSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void stop() {
		brakeSolenoid.set(DoubleSolenoid.Value.kForward);
		driveWinch(0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
