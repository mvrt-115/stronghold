package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Intake extends Command {

	public Intake() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.shooter.drive(-0.80);
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.shooter.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
