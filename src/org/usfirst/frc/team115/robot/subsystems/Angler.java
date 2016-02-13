package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Code for the motors that control the angle of the shooter.
 *
 *@author Ben Cuan, Nolan Nguyen, and Heather Baker
 */


public class Angler extends Subsystem {
	
  private CANTalon[] anglers = new CANTalon[2];
	
	private RobotDrive angler;
	
	private AnalogInput[] hallEffects = new AnalogInput[3];
	
	private final double HALL_EFFECT_TRUE = 3.00; // TODO
	private final double TICKS_PER_DEGREE = 1.00; // TODO
    
	public Angler() {
		anglers[Constants.kLeft] = new CANTalon(RobotMap.ANGLER_LEFT_MOTOR);
		anglers[Constants.kRight] = new CANTalon(RobotMap.ANGLER_RIGHT_MOTOR);
		
		angler = new RobotDrive(anglers[Constants.kLeft], anglers[Constants.kRight]);
		
    hallEffects[Constants.kBottom] = new AnalogInput(RobotMap.ANGLER_BOTTOM_HALL);
    hallEffects[Constants.kMiddle] = new AnalogInput(RobotMap.ANGLER_SHOOT_HALL);
    hallEffects[Constants.kTop] = new AnalogInput(RobotMap.ANGLER_TOP_HALL);
		
		for(CANTalon angler: anglers) {
			angler.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		}
		
		resetEncoders();
	}
	
	public void angle(double speed) {
		angler.arcadeDrive(speed, 0);
	}
	
	public void stop() {
		angle(0);
	}
	
	public void resetEncoders() {
		for(CANTalon angler : anglers) {
			angler.reset();
		}
	}
	
	public double getAngle() {
	  return ((anglers[Constants.kLeft].getPosition() + anglers[Constants.kRight].getPosition()) / (2 * TICKS_PER_DEGREE));
	}
	
	/** 
	 * The hallEffect parameter number is how many hall effects are under the hall effect that is trying to be read.
	 * Example: the number for bottom most hall effect is 0. There are no other hall effects underneath it.
	 * @param hallEffect
	 * @return
	 */
	public boolean isHallEffectTrue(int hallEffect) {
		if(hallEffects[hallEffect].getVoltage() > HALL_EFFECT_TRUE)
			return true;
		else
			return false;
	}
    
	public void initDefaultCommand() {
		
	}
}

