package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Code for the motors that open and close the shooter door.
 *
 *@author Ben Cuan
 */
public class ShooterDoor extends Subsystem {
	
	private final int LEFT = 1;
	private final int RIGHT = 2;
    
	private CANTalon[] shooterDoor = new CANTalon[2];
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public ShooterDoor() {
		shooterDoor[LEFT] = new CANTalon(RobotMap.SHOOTER_DOOR_LEFT);
		shooterDoor[RIGHT] = new CANTalon(RobotMap.SHOOTER_DOOR_RIGHT);
		
		for(CANTalon shooterDOOR: shooterDoor) {
			shooterDOOR.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		}
		
		resetEncoders();
	}
	
	public void open() {
		shooterDoor[LEFT].set(RobotMap.SHOOTER_DOOR_SPEED);
		shooterDoor[RIGHT].set(RobotMap.SHOOTER_DOOR_SPEED);
	}
	
	public void close() {
		shooterDoor[LEFT].set(-RobotMap.SHOOTER_DOOR_SPEED);
		shooterDoor[RIGHT].set(-RobotMap.SHOOTER_DOOR_SPEED);
	}
	
	public void stop() {
		shooterDoor[LEFT].disableControl();
		shooterDoor[RIGHT].disableControl();
	}
	
	public void resetEncoders() {
		for(CANTalon m:shooterDoor) {
			m.reset();
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

