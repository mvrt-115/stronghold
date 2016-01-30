package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *Angles the shooter up at default speed. 
 *Coded for use with a button.
 *@author Ben Cuan
 */
public class AngleShooterUp extends Command {
	
	DigitalInput limitSwitch;
	
    public AngleShooterUp() {
        limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_TOP);
    }
    
    public double getRamp(double percentToTarget) {
		return ((1 - 0.70 / (1 + 12000*Math.exp(-0.115 * percentToTarget))));
	}
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterAngler.goUp();
    }

    // Make this return true when this Command no longer needs to run execute()
   

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterAngler.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		if(Robot.shooterAngler.getLimitSwitchDown(limitSwitch)) return true;
    	else return false;
	}
}
