package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * 
 * @author Rithvik Chuppala
 */

public class LiftArm extends Command {
	
	public LiftArm() {
		requires(Robot.winch);
	}

	@Override
	protected void initialize() {
		Robot.winch.releaseBrake();
		Robot.winch.liftArm();
	}

	@Override
	protected void execute() {
		Robot.winch.outRope();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.winch.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
