package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by admin on 3/17/2016.
 */
public class Batter extends Command {

    public Batter() {
        requires(Robot.angler);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.angler.setOutput(-0.8);
    }

    @Override
    protected boolean isFinished() {
        return Robot.angler.getAngle() <= 0;
    }

    @Override
    protected void end() {
        Robot.angler.setOutput(0);
        Robot.angler.brakeOn();
    }

    @Override
    protected void interrupted() {

    }
}
