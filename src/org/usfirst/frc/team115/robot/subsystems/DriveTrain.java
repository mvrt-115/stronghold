package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;
import org.usfirst.frc.team115.robot.commands.ArcadeDriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drive Train for the 2016 stronghold competition.
 * 
 * @author Amit Palekar and Heather Baker
 *
 */


public class DriveTrain extends Subsystem {
  
    private CANTalon backLeft;
    private CANTalon backRight;
	private CANTalon[] motors = new CANTalon[2];
	private final int LEFT = 0;
	private final int RIGHT = 1;

	private RobotDrive drive;
	
	private final double TICKS_PER_INCH = 1.00;
	
	public DriveTrain() {
		motors[LEFT] = new CANTalon(RobotMap.DRIVE_LEFT_FRONT_MOTOR);
		motors[RIGHT] = new CANTalon(RobotMap.DRIVE_RIGHT_FRONT_MOTOR);
		backLeft = new CANTalon(RobotMap.DRIVE_LEFT_BACK_MOTOR);
		backRight = new CANTalon(RobotMap.DRIVE_RIGHT_BACK_MOTOR);
		
		//Makes talons follow the front
		backLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
		backLeft.set(motors[LEFT].getDeviceID());
		backRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		backRight.set(motors[RIGHT].getDeviceID());
		
		motors[RIGHT].setInverted(true);
		
		drive = new RobotDrive(motors[LEFT], motors[RIGHT]);
	}
	
	public void drive(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}
	
	public void drive(Joystick joystick) {
		drive.arcadeDrive(joystick);
	}
	
	public void control(double leftOutput, double rightOutput) {
		drive.setLeftRightMotorOutputs(leftOutput, rightOutput);
	}
	
	public void stop() {
		drive(0, 0);
	}
	
	public double getDistance() {
	    return ((motors[LEFT].getPosition() + motors[RIGHT].getPosition()) / (2 * TICKS_PER_INCH));
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ArcadeDriveWithJoystick());		
	}
}
