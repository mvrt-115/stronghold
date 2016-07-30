package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by admin on 3/17/2016.
 */
public class Batter extends Command {

    public Batter() {
        setTimeout(1.25);
        requires(Robot.angler);
    }

    @Override
    protected void initialize() {
        Robot.angler.brakeOff();
    }

    @Override
    protected void execute() {
        Robot.angler.setOutput(0.75);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut() || Robot.angler.isTopLimit();
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
