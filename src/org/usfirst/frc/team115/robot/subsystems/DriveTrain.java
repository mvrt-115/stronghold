package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drive Train for the 2016 stronghold competition.
 * 
 * @author Amit Palekar
 *
 */
public class DriveTrain extends Subsystem{
	
	private RobotDrive drive;
	private CANTalon left,left_follower, right, right_follower;
	
	public DriveTrain() {
		left = new CANTalon(RobotMap.FRONT_LEFT_MOTOR);
		left_follower = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR);
		right = new CANTalon(RobotMap.BACK_LEFT_MOTOR);
		right_follower = new CANTalon(RobotMap.BACK_RIGHT_MOTOR);
		
		drive = new RobotDrive(left, right);
		left_follower.changeControlMode(CANTalon.ControlMode.Follower);
		left_follower.set(left.getDeviceID());
		right_follower.changeControlMode(CANTalon.ControlMode.Follower);
		right_follower.set(right.getDeviceID());
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
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub		
	}
}

