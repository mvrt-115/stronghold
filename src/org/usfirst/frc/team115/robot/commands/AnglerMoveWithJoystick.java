package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * 
 * @author Heather Baker and Rithvik Chuppala
 */


public class AnglerMoveWithJoystick extends Command {
  
  public AnglerMoveWithJoystick() {
    requires(Robot.angler);
  }
  
  @Override
  protected void initialize() {
    	
  }
  
  @Override
  protected void execute() {
    Robot.angler.setOutput(Robot.oi.getJoystick().getThrottle());
  }
  
  @Override
  protected boolean isFinished() {
    return Robot.angler.isHallEffectActive(Constants.kBottom)||Robot.angler.isHallEffectActive(Constants.kTop);
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
