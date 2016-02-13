package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunWinchForDistance extends Command {

    private double distance;
    public RunWinchForDistance(double distance) {
      this.distance = distance;
      requires(Robot.winch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      Robot.winch.driveWinch(distance);
      
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      if(Robot.winch.getDistance() > distance) {
        return true;
      } else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.winch.brake();
      Robot.winch.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      end();
    }
}
