package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * 
 * @author Nolan Nyugen and Heather Baker
 */


public class FireShooter extends Command{
	
	public FireShooter() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.shooter.drive(0.80);
		Robot.shooter.punch();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.shooter.retract();
		
	}

	@Override
	protected void interrupted() {
		end();
		
	}

}
