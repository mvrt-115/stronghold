package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveWithJoystick extends Command {

	public ArcadeDriveWithJoystick() {
		requires(Robot.drive);
	}
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.drive.drive(Robot.oi.getJoystick());		
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
