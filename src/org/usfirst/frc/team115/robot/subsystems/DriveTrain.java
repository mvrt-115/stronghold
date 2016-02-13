package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.RobotMap;
import org.usfirst.frc.team115.robot.commands.DriveArcadeWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
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
	private Encoder[] encoders = new Encoder[2];

	private RobotDrive drive;
	
	private final double TICKS_PER_INCH = 1.00; // TODO
	
	public DriveTrain() {
		motors[Constants.kLeft] = new CANTalon(RobotMap.DRIVE_MOTOR_LEFT_FRONT);
		motors[Constants.kRight] = new CANTalon(RobotMap.DRIVE_MOTOR_RIGHT_FRONT);
		backLeft = new CANTalon(RobotMap.DRIVE_MOTOR_LEFT_BACK);
		backRight = new CANTalon(RobotMap.DRIVE_MOTOR_RIGHT_BACK);
		
		//Makes talons follow the front
		backLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
		backLeft.set(motors[Constants.kLeft].getDeviceID());
		backRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		backRight.set(motors[Constants.kRight].getDeviceID());
		
		motors[Constants.kRight].setInverted(true);
		
		drive = new RobotDrive(motors[Constants.kLeft], motors[Constants.kRight]);
		
		encoders[Constants.kLeft] = new Encoder(RobotMap.DRIVE_ENCODER_LEFT_A, RobotMap.FLYWHEEL_ENCODER_LEFT_B, false, Encoder.EncodingType.k4X);
    encoders[Constants.kRight] = new Encoder(RobotMap.DRIVE_ENCODER_RIGHT_A, RobotMap.DRIVE_ENCODER_RIGHT_B, false, Encoder.EncodingType.k4X);
    
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
	    return ((encoders[Constants.kLeft].get() + encoders[Constants.kRight].get()) / (2 * TICKS_PER_INCH));
	}
	
	public void resetEncoders() {
	  for (Encoder encoder : encoders) {
	    encoder.reset();
	  }
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new DriveArcadeWithJoystick());		
	}
}
