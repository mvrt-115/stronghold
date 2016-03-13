package com.mvrt.stronghold.commands;


import com.mvrt.lib.ConstantsBase;
import com.mvrt.lib.PidConstants;
import com.mvrt.lib.SynchronousPid;
import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class drives the robot in a circle at a set amount of degrees
 * @author Marcus Plutowski
 *
 */
public class TurnPID extends Command {
	
	private static final double tolerance = 0.2;
	private static final double onTargetThreshold = 11;
	
	private double setpoint;
	private boolean onTarget;
	private double initial;
	private double target;

  private SynchronousPid controller;
	
	public TurnPID(double degrees, boolean innerLoop) {
		requires(Robot.drive);

    controller = new SynchronousPid(Constants.kDriveTurnKp, Constants.kDriveTurnKi, Constants.kDriveTurnKd);
		this.target = degrees;
		this.onTarget = innerLoop;
	}

	
	@Override
	protected void initialize() {
		SmartDashboard.putBoolean("Entered TurnPID command", true);
    controller.setOutputRange(-0.7, 0.7);
    controller.setInputRange(-180, 180);
    controller.setContinuous(true);

		this.initial = Robot.navx.getYaw();
		setSetpoint((target+initial));
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("Initial", this.initial);

    double angle = Robot.navx.getYaw();

    if(Math.abs(angle - this.setpoint) < this.tolerance) {
      Robot.drive.stop();
    }

		if(onTarget) {
			controller.setPid(Constants.kDriveTurnOnTargetKp, Constants.kDriveTurnOnTargetKi, Constants.kDriveTurnOnTargetKd);
			SmartDashboard.putBoolean("InnerPID", true);
		}
		else {
			controller.setPid(Constants.kDriveTurnKp, Constants.kDriveTurnKi, Constants.kDriveTurnKi);
			SmartDashboard.putBoolean("InnerPID", false);
		}

    double output = controller.calculate(angle);

    Robot.drive.setLeftRightMotorOutputs(output, output);

		SmartDashboard.putNumber("Setpoint", controller.getSetpoint());
		SmartDashboard.putNumber("Error", controller.getError());
		/*if(Math.abs(this.returnPIDInput() - this.setpoint) < onTargetThreshold) {
			System.out.println("OnTarget");
			onTarget = true;
		}*/
	}

	public void setSetpoint(double target){
		SmartDashboard.putNumber("TARGET", target);
		if(target >= 180){
			this.setpoint = target - 360;
		}
		else if(target <= -180){
			this.setpoint = target + 360;
		}
		else {
			this.setpoint = target;
		}
		SmartDashboard.putNumber("SETPOINT",this.setpoint);
		controller.setSetpoint(this.setpoint);
	}
	@Override
	protected boolean isFinished() {
		SmartDashboard.putNumber("dooty isFinished", Robot.navx.getYaw() - this.setpoint);
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {
		
	}

	public void setGoal(double yaw) {
		this.target = yaw;
		this.initial = Robot.navx.getYaw();
		this.setSetpoint(target+initial);
		this.onTarget = false;
	}
	
	
}
