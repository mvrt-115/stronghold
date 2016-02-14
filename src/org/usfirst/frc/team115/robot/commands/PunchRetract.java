package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PunchRetract extends Command {

  public PunchRetract() {
    requires(Robot.punch);
  }

  @Override
  protected void initialize() {
        
  }

  @Override
  protected void execute() {
    Robot.punch.retract();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    
  }

  @Override
  protected void interrupted() {
    end();
  }

}
