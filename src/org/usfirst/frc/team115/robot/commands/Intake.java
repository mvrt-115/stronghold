package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Intake extends Command {

    public Intake() {
        requires(Robot.shooterIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      Robot.shooterIntake.drive(-RobotMap.SHOOTER_INTAKE_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      while(Math.abs(Robot.shooterIntake.getEncoderDistance()) < RobotMap.ENCODER_INTAKE_AMOUNT) {
        return false;
      }
      
      return true;
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.shooterIntake.resetEncoders();
      Robot.shooterIntake.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      end();
    }
}
