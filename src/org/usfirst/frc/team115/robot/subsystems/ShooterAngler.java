package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Code for the motors that control the angle of the shooter.
 *
 *@author Ben Cuan
 */
public class ShooterAngler extends Subsystem {
	
	private final int LEFT = 1;
	private final int RIGHT = 2;
    
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
	
	public void spin() {
		shooterAngler[LEFT].set(RobotMap.SHOOTER_ANGLER_SPEED);
		shooterAngler[RIGHT].set(RobotMap.SHOOTER_ANGLER_SPEED);
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
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

