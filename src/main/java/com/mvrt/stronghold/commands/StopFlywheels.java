package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class StopFlywheels extends Command {

    boolean isFinished = false;

    public StopFlywheels() {
        requires(Robot.leftFlywheel);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.leftFlywheel.stop();
        Robot.rightFlywheel.stop();
        isFinished = true;
    }

    @Override
    protected boolean isFinished() {
        return isFinished;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}