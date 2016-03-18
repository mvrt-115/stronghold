package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StopFlywheels extends CommandGroup {

  public StopFlywheels() {
    Robot.leftFlywheel.stop();
    Robot.rightFlywheel.stop();
  }
}
