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
	
	protected final static double SPEED_DEFAULT = 0.7;
	private double speed;
	private boolean joystick;
	
	public DriveStraight(double p, double i, double d, double speed, boolean joystick) {
		super(p, i, d);
		
		requires(Robot.drive);
		
		this.speed = speed;
		this.joystick = joystick;
	
	}

	@Override
	protected void initialize() {
		
		Robot.drive.stop();
		setInputRange(-180,180);
		getPIDController().setOutputRange(-RobotMap.DRIVE_OUTPUT_RANGE, RobotMap.DRIVE_OUTPUT_RANGE);
		getPIDController().setContinuous(true);
		//getPIDController().setSetpoint(Robot.drive.getYaw()); //TODO
		
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

		return false;
	}

	@Override
	protected void end() {
		
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {

		end();
	}

	@Override
	protected double returnPIDInput() {

		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {

		
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
