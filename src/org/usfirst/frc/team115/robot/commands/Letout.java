/*
 * 
 * 
 * @author Rithvik Chuppala
 */
package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Letout extends Command{
	public Letout(){
		requires(Robot.winch);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.winch.letout();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.winch.stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
