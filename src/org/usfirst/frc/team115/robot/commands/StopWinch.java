package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * 
 * @author Rithvik Chuppala and Heather Baker
 */


public class StopWinch extends Command {
	
	public StopWinch() {
		requires(Robot.winch);
	}
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.winch.stop();
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
