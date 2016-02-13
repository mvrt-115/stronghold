package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveToAngle extends PIDCommand{

	public MoveToAngle(double p, double i, double d, double angle) {
		super(p, i, d);
		requires(Robot.angler);
		setSetpoint(angle);
	}

	@Override
	protected double returnPIDInput() {
		return (getSetpoint() - Robot.angler.getAngle());
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("PID Output", output);
		Robot.angler.setOutput(output);
	}

	@Override
	protected void initialize() {
		getPIDController().setInputRange(0, 90);
		getPIDController().setAbsoluteTolerance(5);
		getPIDController().enable();
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return Robot.angler.getAngle() == getSetpoint();
	}

	@Override
	protected void end() {
		Robot.angler.stop();
	}

	@Override
	protected void interrupted() {}

}
