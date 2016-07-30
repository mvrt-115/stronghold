package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
* Created by Admin on 3/18/2016.
*/
public class Fire extends CommandGroup {

    public Fire(){
        addSequential(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kFlywheelBatterPower));
        addParallel(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kFlywheelBatterPower));
        addSequential(new Delay(3.25));
        addSequential(new Shoot());
        //addSequential(new StopFlywheels());
    }

}
