package com.mvrt.stronghold.commands;

/**
 * Created by Admin on 4/7/2016.
 */

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Created by Admin on 3/18/2016.
 */
public class GenericObstacleAuton extends CommandGroup {

    public GenericObstacleAuton(){
        addSequential(new Batter());
        addSequential(new DriveStraight(0.6, false, 2));
    }

}

