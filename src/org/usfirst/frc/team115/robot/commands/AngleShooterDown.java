package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *Angles the shooter down at default speed. 
 *Coded for use with a button.
 *@author Ben Cuan
 */
public class AngleShooterDown extends Command {
	
	DigitalInput limitSwitch;

    public AngleShooterDown() {
 
    	requires(Robot.shooterAngler);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_BOTTOM);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	
    	Robot.shooterAngler.goDown();
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.shooterAngler.getLimitSwitchDown(limitSwitch)) return true;
    	else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterAngler.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
