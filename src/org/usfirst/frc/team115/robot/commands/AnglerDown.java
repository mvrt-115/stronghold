package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * 
 * @author Heather Baker
 */


public class AnglerDown extends Command {

	public AnglerDown() {
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
		return Robot.angler.isHallEffectTrue(Constants.kBottom);
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
