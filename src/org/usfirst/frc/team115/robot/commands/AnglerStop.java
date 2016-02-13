package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * 
 * @author Heather Baker
 */


public class AnglerStop extends Command {

	public AnglerStop() {
		requires(Robot.angler);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.angler.stop();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
