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
 * @author Amit Palekar
 *
 */
public class DriveTrain extends Subsystem {

	private CANTalon[] motors = new CANTalon[2];
	private RobotDrive drive;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private CANTalon backLeft;
	private CANTalon backRight;
	
	public DriveTrain() {
		motors[LEFT] = new CANTalon(RobotMap.LEFT_MOTOR);
		motors[RIGHT] = new CANTalon(RobotMap.RIGHT_MOTOR);
		backLeft = new CANTalon(RobotMap.LEFT_BACK_MOTOR);
		backRight = new CANTalon(RobotMap.RIGHT_BACK_MOTOR);
		
		backLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
		backLeft.set(motors[LEFT].getDeviceID());
		backRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		backRight.set(motors[RIGHT].getDeviceID());
		
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
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ArcadeDriveWithJoystick());		
	}
}
