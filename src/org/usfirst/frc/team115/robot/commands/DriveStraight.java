package org.usfirst.frc.team115.robot.commands;
/**
 * Drives the robot straight to a setpoint using PID.
 * 
 * @author Ben Cuan
 */
import org.usfirst.frc.team115.robot.Robot;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends PIDCommand {
	
	
	private double speed;
	private boolean joystick;
	
	public DriveStraight(double p, double i, double d, double speed, boolean joystick) {
		super(p, i, d);
		
		requires(Robot.drive);
		
		this.speed = speed;
		this.joystick = joystick;
		// TODO Auto-generated constructor stub
	}



	@Override
	protected void initialize() {
		
		Robot.drive.stop();
		setInputRange(-180,180);
		getPIDController().setOutputRange(-RobotMap.DRIVE_OUTPUT_RANGE, RobotMap.DRIVE_OUTPUT_RANGE);
		getPIDController().setContinuous(true);
		getPIDController().setSetpoint(Robot.drive.getYaw());
		
	}

	public double getSpeed(){
		return speed;
	}
	
	@Override
	protected void execute() {
		// see usePIDOutput
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
		SmartDashboard.putNumber("Drive Output", output);
		if(joystick) {
			//drive with joystick
		Robot.drive.drive(-Robot.oi.getJoystick().getY() * getSpeed(), output);
		}
		else {
			//drive without joystick
			Robot.drive.drive(getSpeed(), output);
		}
	}
}
