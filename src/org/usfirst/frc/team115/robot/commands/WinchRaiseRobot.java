package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * 
 * @author Rithvik Chuppala and Heather Baker
 */


public class WinchRaiseRobot extends Command {
  
  public WinchRaiseRobot() {
    requires(Robot.winch);
  }
  
  @Override
  protected void initialize() {
    Robot.winch.releaseBrake();
  }
  
  @Override
  protected void execute() {
    Robot.winch.driveWinch(-0.5);
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }
  
  @Override
  protected void end() {
    Robot.winch.stop();
  }
  
  @Override
  protected void interrupted() {
    end();
  }
  
}
