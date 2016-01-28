package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterToAngle extends Command {

	private static final int UP = 1;
	private static final int DOWN = -1;
	private double destAngle;
	private double distance;
	private double direction;
	
    public ShooterToAngle(double destAngle) {
    	requires(Robot.shooterAngler);
    	this.destAngle = destAngle;
       
    }
    
    public double getRamp(double percentToTarget) {
		return ((1 - 0.70 / (1 + 12000*Math.exp(-0.115 * percentToTarget))));
	}

    
    // Called just before this Command runs the first time
    protected void initialize() {
		distance = Math.abs(Robot.shooterAngler.getHeight() - destAngle);
		direction = destAngle < Robot.shooterAngler.getHeight() ? DOWN : UP;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x = (100 * Math.abs((distance - Math.abs(Robot.shooterAngler.getHeight() - destAngle)) / distance));
		double ramp = getRamp(x);

		Robot.shooterAngler.setSpeed(direction * RobotMap.SHOOTER_ANGLER_SPEED * ramp);
		/*DEBUG*/SmartDashboard.putNumber("ElevatorSpeed", direction*RobotMap.SHOOTER_ANGLER_SPEED*ramp);
		//SmartDashboard.putNumber("Ramp", ramp);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(direction == UP){
			return (Robot.shooterAngler.getHeight() >= destAngle);
		}
		else{
			return (Robot.shooterAngler.getHeight() <= destAngle);
		}
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
