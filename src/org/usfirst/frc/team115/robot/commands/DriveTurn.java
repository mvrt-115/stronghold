package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * 
 * @author Heather Baker
 */


public class DriveTurn extends PIDCommand {
  
  private final double THRESHOLD = 0.05;
  protected static final double DEFAULT_SPEED = 0.5;
  private static final double P = 0.0;
  private static final double I = 0.0;
  private static final double D = 0.0;
  private double speed;
  private double angle;
  private double destination = 0.0;
  private double distLeft;
    
  public DriveTurn(double angle) {
    this(DEFAULT_SPEED, angle);
  }
    
  public DriveTurn(double speed, double angle) {
    super(P, I, D);
    this.speed = speed;
    this.angle = angle;
    distLeft = this.angle;
    requires(Robot.drive);
  }
  
  @Override
  protected double returnPIDInput() {
    return Robot.navx.getYaw();
  }
  
  @Override
  protected void usePIDOutput(double output) {
    SmartDashboard.putNumber("DriveTurnOuput", output);
    Robot.drive.drive(0, output);
  }
  
  @Override
  protected void initialize() {
    Robot.drive.stop();
    destination = angle + Robot.navx.getYaw();
    setInputRange(-180, 180); // Set range from -180 to 180 degrees. The input is obtained from navx
    getPIDController().setOutputRange(-0.6, 0.6); // Value to pass to output for driving.
    getPIDController().setContinuous(true);
    setSetpoint(destination); // Set the current location to goal to keep on track    
  }
  
  @Override
  protected void execute() {
    // No code in here, all done in usePIDOutput
  }
  
  protected double absoluteDistanceLeft() {
    distLeft = destination - Robot.navx.getYaw();
    if (distLeft < 0)
      return -distLeft;
    return distLeft;
  }
  
  @Override
  protected boolean isFinished() {
    return absoluteDistanceLeft() <= THRESHOLD;
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
