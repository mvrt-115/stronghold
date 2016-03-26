package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
* Created by Admin on 3/18/2016.
*/
public class Fire extends CommandGroup {

  public Fire() {
    addSequential(new SetFlywheelPower(Robot.leftFlywheel, 0.25));
    addSequential(new SetFlywheelPower(Robot.rightFlywheel, 0.25));
    addSequential(new DelayCommand(new Fire(), 0.5));
    addSequential(new StopFlywheels());
  }
}
