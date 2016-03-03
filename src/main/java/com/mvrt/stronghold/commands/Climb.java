package src.main.java.com.mvrt.stronghold.commands;

import edu.wpi.first.wpilibj.command.Command;
import src.main.java.com.mvrt.stronghold.Constants;
import src.main.java.com.mvrt.stronghold.Robot;

public class Climb extends Command {

	public Climb() {
		requires(Robot.climber);
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		Robot.climber.winchUp();
	}

	protected boolean isFinished() {
		return !(Robot.operatorInterface.getOperatorJoystick().getRawButton(Constants.kClimberButton));
	}

	protected void end() {
		Robot.climber.stop();
	}

	protected void interrupted() {
		end();
	}

}
