package src.main.java.com.mvrt.stronghold.commands;

import edu.wpi.first.wpilibj.command.Command;
import src.main.java.com.mvrt.stronghold.Robot;

public class ExtendClimber extends Command {

	boolean isFinished = false;
	
	public ExtendClimber() {
		requires(Robot.climber);
	}
	
	protected void initialize() {}

	protected void execute() {
		Robot.climber.extendClimber();
		isFinished = true;
	}

	protected boolean isFinished() {
		return isFinished;
	}

	protected void end() {}

	protected void interrupted() {}

}
