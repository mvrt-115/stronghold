package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FlyWheelsIntake extends Command {

  public FlyWheelsIntake() {
    requires(Robot.flyWheels);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.flyWheels.intake();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.flyWheels.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }

}
