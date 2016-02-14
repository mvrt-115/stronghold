package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * 
 * @author Heather Baker
 */


public class DriveArcadeWithJoystick extends Command {

	private double scalar = 1.0;
	private static final double DEFAULT_PRECISION = 0.75;
	
	public DriveArcadeWithJoystick() {
		this(false);
	}
	
	public DriveArcadeWithJoystick(boolean precision) {
		requires(Robot.drive);
		if (precision)
			scalar = DEFAULT_PRECISION;
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.drive.drive(Robot.oi.getJoystick().getY() * scalar, Robot.oi.getJoystick().getX() * scalar);		
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

}
