package com.mvrt.stronghold.indep;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import com.mvrt.stronghold.commands.AnglerMoveToAngle;
import com.mvrt.stronghold.commands.DelayCommand;
import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import com.mvrt.stronghold.commands.Shoot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BatterShot extends CommandGroup {

  public BatterShot() {
    addSequential(new AnglerMoveToAngle(Constants.kAnglerBatterPreset));
    addSequential(new SetFlywheelSpeed(Robot.leftFlywheel, Constants.kBatterShootSpeed));
    addParallel(new SetFlywheelSpeed(Robot.rightFlywheel, Constants.kBatterShootSpeed));
    addSequential(new DelayCommand(new Shoot(), 5));
  }
}