package com.mvrt.stronghold.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DelayCommand extends CommandGroup {
  public DelayCommand(Command c, double time) {
    addSequential(new Delay(time));
    addSequential(c);
  }
}
