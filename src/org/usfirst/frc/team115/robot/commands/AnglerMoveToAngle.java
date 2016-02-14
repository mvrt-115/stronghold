package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AnglerMoveToAngle extends PIDCommand {
  
  private static final double P = 0;
  private static final double I = 0;
  private static final double D = 0;
  
  private final double THRESHOLD = 0.05;
  
  private double angle;

	public AnglerMoveToAngle(double angle) {
		super(P, I, D);
		requires(Robot.angler);
		this.angle = angle;
	}

	@Override
	protected double returnPIDInput() {
		return Robot.angler.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("PID Output", output);
		Robot.angler.setOutput(output);
	}

	@Override
	protected void initialize() {
		getPIDController().setInputRange(0, 90);
		getPIDController().setOutputRange(-0.6, 0.6);
		setSetpoint(angle);
	}
	
	protected double getAngleLeft() {
	  return Math.abs(angle - Robot.angler.getAngle());
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return (getAngleLeft() <= THRESHOLD)||(Robot.angler.isHallEffectTrue(Constants.kBottom)||Robot.angler.isHallEffectTrue(Constants.kTop));
	}

	@Override
	protected void end() {
		Robot.angler.stop();
	}

	@Override
	protected void interrupted() {
	  end();
	}

}
