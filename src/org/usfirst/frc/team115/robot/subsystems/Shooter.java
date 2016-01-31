package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	private CANTalon left, right;
	private RobotDrive shooter;
	public Shooter() {
		left = new CANTalon(RobotMap.SHOOTER_LEFT_MOTOR);
		right = new CANTalon(RobotMap.SHOOTER_RIGHT_MOTOR);
		shooter = new RobotDrive(left, right);
	}
	
	public void shoot(Joystick joystick) {
		shooter.arcadeDrive(joystick);
	}
	
	public void shoot(double move) {
		shooter.arcadeDrive(move, 0);
	}
	
	public void stop() {
		shoot(0);
	}
	
	
	@Override
	protected void initDefaultCommand() {
	}

}
