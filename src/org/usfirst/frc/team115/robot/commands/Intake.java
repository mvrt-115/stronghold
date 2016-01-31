package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Intake extends Command {
	private static final double INTAKE_SPEED = -0.6;
	public Intake() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		Robot.shooter.shoot(INTAKE_SPEED);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		Robot.shooter.stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}
}
