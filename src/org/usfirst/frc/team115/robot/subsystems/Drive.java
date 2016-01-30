package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem{
	
	private final int Right_Front = 0;
	private final int Right_Back = 1;
	private final int Left_Front = 2;
	private final int Left_Back = 3;

	private CANTalon[] motors = new CANTalon[4];
		
	private RobotDrive drive;
	
	public Drive() {
		
		motors[Right_Front] = new CANTalon(RobotMap.DriveRightFrontMotor);
		motors[Right_Back] = new CANTalon(RobotMap.DriveRightBackMotor);
		motors[Left_Front] = new CANTalon(RobotMap.DriveLeftFrontMotor);
		motors[Left_Back] = new CANTalon(RobotMap.DriveLeftBackMotor);
		
		drive = new RobotDrive(motors[Left_Front], motors[Right_Front]);
		
		motors[Right_Back].changeControlMode(CANTalon.TalonControlMode.Follower);
		motors[Right_Back].set(motors[Right_Front].getDeviceID());
		motors[Left_Back].changeControlMode(CANTalon.TalonControlMode.Follower);
		motors[Left_Back].set(motors[Left_Front].getDeviceID());
		
	}
	
	public void control(Joystick joystick) {
		drive.arcadeDrive(joystick);
	}
	
	public void control(double move, double rotate){
		drive.arcadeDrive(move * -1, rotate);
	}
	
	public void control(Joystick joystick, double speed) {
		drive.arcadeDrive(joystick.getY() * speed, joystick.getX() * speed);
	}

	@Override
	protected void initDefaultCommand() {
		
	}

	public void stop() {
		
	}
	
}
