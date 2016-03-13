package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Fire extends CommandGroup {
  public Fire() {
    addSequential(new AnglerMoveToAngle(Constants.kAnglerBatterPreset));
    addSequential(new SetFlywheelSpeed(Robot.leftFlywheel));
    addParallel(new SetFlywheelSpeed(Robot.rightFlywheel));
    addSequential(new Shoot());
    addSequential(new AnglerMoveToAngle(Constants.kAnglerBottomPreset));
  }
}
