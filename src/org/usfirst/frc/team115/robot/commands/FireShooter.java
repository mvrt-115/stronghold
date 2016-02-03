package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class FireShooter extends Command{
	
	public FireShooter() {
		requires(Robot.shooterIntake);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		Robot.shooterIntake.drive(0.80);
		Robot.shooterIntake.punch();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.shooterIntake.stop();
		Robot.shooterIntake.retract();
		
	}

	@Override
	protected void interrupted() {
		end();
		
	}

}
