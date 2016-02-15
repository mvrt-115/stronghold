package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * 
 * @author Heather Baker
 */

public class DriveStraight extends PIDCommand {
  
  protected final static double DEFAULT_SPEED = 0.75;
  private final static double P = 0;
  private final static int I = 0;
  private final static int D = 0;
  
  private boolean joystick;
  private double speed;
  
  /**
   * Drives with a default speed. If the joystick is true, drives using the y-axis. Else goes forward at default speed
   * @param joystick
   */
  public DriveStraight(boolean joystick) {
    this(DEFAULT_SPEED, joystick);
  }
  
  /**
   * Drives using the speed set from the parameter.
   * @param speed
   * @param joystick
   */
  public DriveStraight(double speed, boolean joystick) {
    super(P, I, D);
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
    if (joystick) {
      Robot.drive.drive(Robot.oi.getJoystick().getY() * speed, output);
    } else {
      Robot.drive.drive(speed, output);
    }
  }

  @Override
  protected void initialize() {
    Robot.drive.stop();
    setInputRange(-180, 180); // Set range from -180 to 180 degrees. The input is obtained from navx
    getPIDController().setOutputRange(-0.6, 0.6); // Value to pass to output for driving.
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
