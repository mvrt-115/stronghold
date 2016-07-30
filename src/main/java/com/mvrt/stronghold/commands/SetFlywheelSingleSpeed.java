package com.mvrt.stronghold.commands;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import com.mvrt.stronghold.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;

public class SetFlywheelSingleSpeed extends Command {

    private Flywheel referenceFlywheel;

    private final double speed;

    private boolean finished = false;

    private boolean willTimeout = false;

    public SetFlywheelSingleSpeed(double speed) {
        this.speed = speed;
        requires(Robot.leftFlywheel);
    }

    public SetFlywheelSingleSpeed(double speed, double timeout) {
        this.speed = speed;

        setTimeout(timeout);
        willTimeout = true;
        requires(Robot.leftFlywheel);
    }

    @Override
    protected void initialize() {
        finished = false;
        Robot.leftFlywheel.disable();
    }

    @Override
    protected void execute() {
        if (Robot.leftFlywheel.isNearTarget()) {
            Robot.leftFlywheel.getPIDController().setPID(Constants.kFlywheelKp, Constants.kFlywheelKi,
                    Constants.kFlywheelKd);
        } else {
            Robot.leftFlywheel.getPIDController().setPID(Constants.kFlywheelKpOnTarget,
                    Constants.kFlywheelKiOnTarget, Constants.kFlywheelKdOnTarget);
        }
        if(!finished) {
            //referenceFlywheel.setSpeed(speed);
            Robot.leftFlywheel.setSetpoint(speed);
            Robot.leftFlywheel.enable();
            finished = true;
        }
    }

    @Override
    protected boolean isFinished() {
        return finished && !willTimeout;
    }

    @Override
    protected void end() {
        if(willTimeout){
            Robot.leftFlywheel.stop();
            Robot.rightFlywheel.stop();
        }
    }

    @Override
    protected void interrupted() {}
}
