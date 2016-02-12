package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * 
 * @author Eric Wang
 */

public class StopShooter extends Command {
  public StopShooter() {
	requires(Robot.shooter);
  }

  @Override
	protected void initialize() {

  }

	@Override
	protected void execute() {
		Robot.shooter.stop();
		Robot.shooter.retract();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();

	}

}
