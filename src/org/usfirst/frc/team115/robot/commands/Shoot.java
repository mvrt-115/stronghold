package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
    
    private double shootDistance;
    public Shoot(double shootDistance) {
        requires(Robot.shooterIntake);
        this.shootDistance = shootDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
     
        Robot.shooterIntake.drive(RobotMap.SHOOTER_INTAKE_SPEED);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      while(Robot.shooterIntake.getEncoderDistance() < shootDistance) {
        return false;
      }
      
      return true;
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.shooterIntake.stop();
      Robot.shooterIntake.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      end();
    }
}
