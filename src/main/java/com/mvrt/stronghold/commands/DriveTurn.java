package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTurn extends Command {
  private final double goal;

  public DriveTurn(double goal) {
    this.goal = goal;

    requires(Robot.drive);
  }

  @Override
  protected void initialize() {
    Robot.drive.setYawGoal(goal);
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return Robot.drive.turnOnTarget();
  }

  @Override
  protected void end() {
    Robot.drive.disableController();
  }

  @Override
  protected void interrupted() {
    Robot.drive.disableController();
  }
}
