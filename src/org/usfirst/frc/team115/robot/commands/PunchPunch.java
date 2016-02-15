package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PunchPunch extends Command {

  public PunchPunch() {
    requires(Robot.punch);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.punch.punch();

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
