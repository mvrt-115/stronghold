package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightForDistanceNoPID extends Command {

    private double speed;
    private double distance;
    
    public DriveStraightForDistanceNoPID(double speed, double distance) {
       requires(Robot.drive);
       
       this.speed = speed;
       this.distance = distance;
    }
    
    public DriveStraightForDistanceNoPID(double distance) {
      requires(Robot.drive);
      
      this.distance = distance;
      speed = RobotMap.DEFAULT_SPEED;
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      Robot.drive.control(speed, -speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.drive.getDistance() > distance) return true;
        else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      end();
    }
}
