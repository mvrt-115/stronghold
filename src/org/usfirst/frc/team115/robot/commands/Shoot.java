package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;
import org.usfirst.frc.team115.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command{
	private static final double SHOOT_SPEED = 0.6;
	public Shoot()
	{
		requires(Robot.shooter);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.shooter.shoot(SHOOT_SPEED);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}
}
