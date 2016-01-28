package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Code for the motors that control the angle of the shooter.
 *
 *@author Ben Cuan
 */
public class ShooterAngler extends Subsystem {
	
	private final int LEFT = 1;
	private final int RIGHT = 2;

	Encoder anglerEncoder = new Encoder(RobotMap.ENCODER_ANGLER_A, RobotMap.ENCODER_ANGLER_B, false, Encoder.EncodingType.k4X);
	
	private CANTalon[] shooterAngler = new CANTalon[2];
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public ShooterAngler() {
		shooterAngler[LEFT] = new CANTalon(RobotMap.SHOOTER_ANGLER_LEFT);
		shooterAngler[RIGHT] = new CANTalon(RobotMap.SHOOTER_ANGLER_RIGHT);
		
		
		
		for(CANTalon shooterAngler: shooterAngler) {
			shooterAngler.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			
		}
		
		resetEncoders();
	}
	
	public void angleUp(int degrees) {
		while(anglerEncoder.getDistance() < RobotMap.ENCODER_ONE_DEGREE * degrees) {
			shooterAngler[LEFT].set(RobotMap.SHOOTER_ANGLER_SPEED);
			shooterAngler[RIGHT].set(RobotMap.SHOOTER_ANGLER_SPEED);
		}
		
		stop();
		
	}
	
	public void angleDown(int degrees) {
		while(anglerEncoder.getDistance() < RobotMap.ENCODER_ONE_DEGREE * degrees) {
			shooterAngler[LEFT].set(-RobotMap.SHOOTER_ANGLER_SPEED);
			shooterAngler[RIGHT].set(-RobotMap.SHOOTER_ANGLER_SPEED);
		}
		
	
		stop();
		
	}
	
	public void goUp() {
		shooterAngler[LEFT].set(RobotMap.SHOOTER_ANGLER_SPEED);
		shooterAngler[RIGHT].set(RobotMap.SHOOTER_ANGLER_SPEED);
	}
	
	public void goDown() {
		shooterAngler[LEFT].set(-RobotMap.SHOOTER_ANGLER_SPEED);
		shooterAngler[RIGHT].set(-RobotMap.SHOOTER_ANGLER_SPEED);
	}

	public void setSpeed(double speed) {
		shooterAngler[LEFT].set (speed);
		shooterAngler[RIGHT].set (speed);
	}
	public void stop() {
		shooterAngler[LEFT].disableControl();
		shooterAngler[RIGHT].disableControl();
	}
	
	public void resetEncoders() {
		for(CANTalon m:shooterAngler) {
			m.reset();
		}
	}
	
	public double getHeight() {
		return RobotMap.BOTTOM_HEIGHT - shooterAngler[LEFT].getPosition() / RobotMap.ENCODER_ONE_DEGREE;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

