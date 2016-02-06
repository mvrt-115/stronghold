package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the shooter intake motors for a specified distance (no PID)
 * @author Ben Cuan
 */

public class RunShooterForDistance extends Command {

    private double distance;
    
    public RunShooterForDistance(double distance) {
        requires(Robot.shooter);
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      Robot.shooter.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      Robot.shooter.drive(distance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      if(Robot.shooter.getDistance() > distance) {
        return true;
      } else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.shooter.resetEncoders();
      Robot.shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      end();
    }
}
