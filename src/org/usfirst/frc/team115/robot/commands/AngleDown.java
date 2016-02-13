package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * 
 * @author Heather Baker
 */


public class AngleDown extends Command {

	public AngleDown() {
		requires(Robot.angler);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.angler.setOutput(-0.5);
	}

	@Override
	protected boolean isFinished() {
		return Robot.angler.isBottomHallTrue();
	}

	@Override
	protected void end() {
		Robot.angler.stop();
		
	}

	@Override
	protected void interrupted() {
		end();
		
	}

}
