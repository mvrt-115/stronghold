/*
 * 
 * 
 * @author Rithvik Chuppala 
 */


package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
	 // make two CANTalons
	private CANTalon[] motors = new CANTalon[2];
	private final int LEFT = 0;
	private final int RIGHT = 1;
	RobotDrive winch;
	public Winch(){
		// Instantiate 2 talons
		motors[LEFT] = new CANTalon(LEFT);
		motors[RIGHT] = new CANTalon(RIGHT);
		RobotDrive winch = new RobotDrive(motors[LEFT], motors[RIGHT]);
	}
	//Robot DRIVES a WINCH to its soccer practice
	public void driveWinch(double move){
		winch.arcadeDrive(move,0);
	}
	//Robot does a PULLUP
	public void pullup(){
		Robot.doublesolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.diskbreak.set(DoubleSolenoid.Value.kReverse);
		driveWinch(0.5);
	}
	//Robot gets LETOUT of its crossbar prison
	public void letout (){
		Robot.doublesolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.diskbreak.set(DoubleSolenoid.Value.kReverse);
		driveWinch(-0.5);
	}
	//Robot STOPs being annoying
	public void stop(){
		Robot.doublesolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.diskbreak.set(DoubleSolenoid.Value.kForward);
		driveWinch(0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
