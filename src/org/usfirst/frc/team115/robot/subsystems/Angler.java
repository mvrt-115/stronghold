package org.usfirst.frc.team115.robot.subsystems;

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
 *@author Ben Cuan & Nolan Nguyen
 */
public class Angler extends Subsystem {
	
	private final int LEFT = 0;
	private final int RIGHT = 1;
	private AnalogInput bottomHall, topHall, shootHall;
	
	private CANTalon[] shooterAngler = new CANTalon[2];
	private RobotDrive angler;
	
	private final double HALL_EFFECT_TRUE = 3.00;
    
	public Angler() {
		shooterAngler[LEFT] = new CANTalon(RobotMap.SHOOTER_ANGLER_LEFT_MOTOR);
		shooterAngler[RIGHT] = new CANTalon(RobotMap.SHOOTER_ANGLER_RIGHT_MOTOR);
		bottomHall = new AnalogInput(RobotMap.ANGLER_BOTTOM_HALL);
		shootHall = new AnalogInput(RobotMap.ANGLER_SHOOT_HALL);
		topHall = new AnalogInput(RobotMap.ANGLER_TOP_HALL);
		
		angler = new RobotDrive(shooterAngler[LEFT], shooterAngler[RIGHT]);
		
		for(CANTalon shooterAngler: shooterAngler) {
			shooterAngler.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			
		}
		
		resetEncoders();
	}
	
	public void angle(int speed) {
		angler.arcadeDrive(speed, 0);
		
	}
	
	public void stop() {
		angle(0);
	}
	
	public void resetEncoders() {
		for(CANTalon m:shooterAngler) {
			m.reset();
		}
	}
	
	public double getHeight() {
		return RobotMap.BOTTOM_HEIGHT - shooterAngler[LEFT].getPosition() / RobotMap.ENCODER_ONE_DEGREE;
	}
	
	public boolean isShootHall() {
		if(shootHall.getVoltage() > HALL_EFFECT_TRUE)
			return true;
		else
			return false;
	}
	
	public boolean isBottomHallTrue(AnalogInput sensor) {
		if(bottomHall.getVoltage() > HALL_EFFECT_TRUE)
			return true;
		else
			return false;
	}
	
	public boolean isTopHallTrue() {
		if(topHall.getVoltage() > HALL_EFFECT_TRUE)
			return true;
		else
			return false;
	}
    
	public void initDefaultCommand() {
		
	}
}

