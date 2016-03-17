package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Fire extends CommandGroup {

  public Fire() {
    addSequential (new AnglerMoveToAngle(Constants.kAnglerBatterPreset));
    addSequential (new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kFlywheelBatterSpeedRPM, 1));
    addParallel (new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kFlywheelBatterSpeedRPM, 1));
    addParallel (new DelayCommand(new Fire(), 0.7));
  }
}
