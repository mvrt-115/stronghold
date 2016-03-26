package com.mvrt.stronghold.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Created by Admin on 3/18/2016.
 */
public class DriveStraightAuton extends CommandGroup {

    public DriveStraightAuton(){
        addSequential(new LowBar());
        addSequential(new DriveStraight(0.4, false, 2.8));
    }

}
