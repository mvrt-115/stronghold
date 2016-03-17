package com.mvrt.stronghold.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DelayCommand extends CommandGroup {

  public DelayCommand(Command command, double delay) {
    addSequential(new Delay(delay));
    addSequential(command);
  }

}
