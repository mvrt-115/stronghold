package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.PIDCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** This class drives the robot in a circle at a set amount of degrees.
* @author Marcus Plutowski
*
*/
public class TurnPid extends PIDCommand {

  private static final double tolerance = 0.2;
  private static final double onTargetThreshold = 11;

  private double setpoint;
  private boolean onTarget;
  private double initial;
  private double target;

  public TurnPid(double degrees, boolean innerLoop) {
    super(Constants.kDriveTurnKp,
        Constants.kDriveTurnKi, Constants.kDriveTurnKd);
    requires(Robot.drive);
    this.target = degrees;
    this.onTarget = innerLoop;
  }

  @Override
  protected double returnPIDInput() {
    SmartDashboard.putNumber("Yaw", Robot.navx.getYaw());
    return Robot.navx.getYaw();
  }

  @Override
  protected void usePIDOutput(double output) { //-output because idk
    SmartDashboard.putNumber("Output", -output);
    Robot.drive.drive(0, -output);
  }

  @Override
  protected void initialize() {
    SmartDashboard.putBoolean("Entered TurnPID command", true);
    setInputRange(-180, 180);
    getPIDController().setOutputRange(-0.7, 0.7);
    getPIDController().setContinuous(true);
    getPIDController().setAbsoluteTolerance(tolerance);

    this.initial = Robot.navx.getYaw();
    setSetpoint((target + initial));
    this.onTarget = false;
  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("Initial", this.initial);
    if (onTarget) {
      getPIDController().setPID(Constants.kDriveTurnOnTargetKp,
          Constants.kDriveTurnOnTargetKi, Constants.kDriveTurnOnTargetKd);
      SmartDashboard.putBoolean("InnerPID", true);
    } else {
      getPIDController().setPID(Constants.kDriveTurnKp,
          Constants.kDriveTurnKi, Constants.kDriveTurnKi);
      SmartDashboard.putBoolean("InnerPID", false);
    }
    setSetpoint((target + initial));
    SmartDashboard.putNumber("Setpoint", getPIDController().getSetpoint());
    SmartDashboard.putNumber("Error", getPIDController().getError());
    /*if (Math.abs(this.returnPIDInput() - this.setpoint) < onTargetThreshold) {
      System.out.println("OnTarget");
      onTarget = true;
    }*/
  }

  public void setSetpoint(double target) {
    SmartDashboard.putNumber("TARGET", target);
    if (target >= 180) {
      this.setpoint = target - 360;
    } else if (target <= -180) {
      this.setpoint = target + 360;
    } else {
      this.setpoint = target;
    }
    SmartDashboard.putNumber("SETPOINT",this.setpoint);
    getPIDController().setSetpoint(this.setpoint);
  }

  @Override
  protected boolean isFinished() {
    SmartDashboard.putNumber("dooty isFinished",
        this.returnPIDInput() - this.setpoint);
    return Math.abs(this.returnPIDInput() - this.setpoint) < this.tolerance;
  }

  @Override
  protected void end() {
    Robot.drive.stop();
  }

  @Override
  protected void interrupted() {}

  public void setGoal(double yaw) {
    this.target = yaw;
    this.initial = Robot.navx.getYaw();
    this.setSetpoint(target + initial);
    this.onTarget = false;
  }
}
