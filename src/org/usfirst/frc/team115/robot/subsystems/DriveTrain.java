package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.RobotMap;
import org.usfirst.frc.team115.robot.commands.DriveArcadeWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
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

	private RobotDrive drive;
	
	private final double TICKS_PER_INCH = 1.00; // TODO
	
	public DriveTrain() {
		motors[Constants.kLeft] = new CANTalon(RobotMap.DRIVE_LEFT_FRONT_MOTOR);
		motors[Constants.kRight] = new CANTalon(RobotMap.DRIVE_RIGHT_FRONT_MOTOR);
		backLeft = new CANTalon(RobotMap.DRIVE_LEFT_BACK_MOTOR);
		backRight = new CANTalon(RobotMap.DRIVE_RIGHT_BACK_MOTOR);
		
		//Makes talons follow the front
		backLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
		backLeft.set(motors[Constants.kLeft].getDeviceID());
		backRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		backRight.set(motors[Constants.kRight].getDeviceID());
		
		motors[Constants.kRight].setInverted(true);
		
		drive = new RobotDrive(motors[Constants.kLeft], motors[Constants.kRight]);
		
		for(CANTalon motor: motors) {
      motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    }
    
    resetEncoders();
		
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
	    return ((motors[Constants.kLeft].getPosition() + motors[Constants.kRight].getPosition()) / (2 * TICKS_PER_INCH));
	}
	
	public void resetEncoders() {
	  for (CANTalon motor: motors) {
	    motor.reset();
	  }
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new DriveArcadeWithJoystick());		
	}
}
