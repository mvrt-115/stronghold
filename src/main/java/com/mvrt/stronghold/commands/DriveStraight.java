package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends PIDCommand {

  private boolean joystick;
  private double speed;

  /**
   * Drives with a default speed.
   * If the joystick is true, drives using the y-axis.
   * Else goes forward at default speed.
   * @param joystick if there is a joystick
   */
  public DriveStraight(boolean joystick) {
    this(Constants.kDriveDefaultSpeed, joystick);
  }

  /**
   * Drives using the speed set from the parameter.
   * @param speed The speed to drive at
   * @param joystick If there this a joystick
   */
  public DriveStraight(double speed, boolean joystick) {
    super(Constants.kDriveKp, Constants.kDriveKi, Constants.kDriveKd);
    requires(Robot.drive);
    this.joystick = joystick;
    this.speed = speed;
  }

  @Override
  protected double returnPIDInput() {
    return Robot.navx.getYaw(); // Uses yaw as input to check if robot is off.
  }

  /**
   * Output is used to control the rotation of the robot. Using PID to keep the robot from rotating.
   */
  @Override
  protected void usePIDOutput(double output) {
    SmartDashboard.putNumber("DriveStraightOutput", output);
    SmartDashboard.putNumber("DriveStraight Error", Robot.navx.getYaw() - getSetpoint());
    if (Math.abs(Robot.navx.getYaw() - getSetpoint()) < 1) {
      getPIDController().setPID(Constants.kDriveKp, 0, Constants.kDriveKi);
    } else {
      getPIDController().setPID(Constants.kDriveKp, Constants.kDriveKi, Constants.kDriveKd);
    }
    if (joystick) {
      Robot.drive.drive(Robot.operatorInterface.getDriveJoystick().getY() * speed, output);
    } else {
      Robot.drive.drive(speed, output);
    }
  }

  @Override
  protected void initialize() {
    Robot.drive.stop();
    setInputRange(-180, 180); // Set range from -180 to 180 degrees. The input is obtained from navx
    getPIDController().setOutputRange(-0.8, 0.8); // Value to pass to output for driving.
    getPIDController().setContinuous(true);
    setSetpoint(Robot.navx.getYaw()); // Set the current location to goal to keep on track
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.drive.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
