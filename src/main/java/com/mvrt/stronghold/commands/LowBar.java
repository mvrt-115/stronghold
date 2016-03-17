package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBar extends CommandGroup {

  public LowBar() {
    addSequential (new AnglerMoveToAngle(0));
    Robot.angler.brakeOff();
  }
}
