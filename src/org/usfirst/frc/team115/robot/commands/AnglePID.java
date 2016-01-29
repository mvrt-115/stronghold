package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AnglePID extends PIDCommand {

	private double goal;
    private double initial;
    public final static double CompP = 0.205; //Tested PID values for use in competition running
    public final static double CompI = 0.006;
    public final static double CompD = 0.002;

    boolean past = false;

    public AnglePID(double goal, final double P, final double I, final double D) { // Custom access to PID values
        super(P, I, D);
    	requires(Robot.drive);
        this.goal = goal;
        
        getPIDController().setContinuous(true);
        setInputRange(0, 360);
        getPIDController().setOutputRange(-0.65, 0.65);
    }

    public AnglePID(double goal) { // Pre-PID accessibility case for backwards compatibility and easy usage of function; just uses the hardcoded PID values we'll have tested
    	this(goal, CompP, CompI, CompD);
    }

    /**
     * @return the gyro's current angle
     */
    @Override
    protected double returnPIDInput() {
        return Robot.drive.getYaw();
    }

    /**
     * Drives the robot forward at the joystick's speed, and at the output angle.
     * @param output: angle to turn
     */
    @Override
    protected void usePIDOutput(double output) {
    	Robot.drive.drive(0, output);
    	SmartDashboard.putNumber("Turn Output", output);
    }

    @Override
    protected void initialize() {
    	
        getPIDController().setAbsoluteTolerance(7); //set 5 degree tolerance
    	initial = Robot.drive.getYaw();
    	setSetpoint((initial + goal)%360);
    	SmartDashboard.putNumber("Turn Setpoint", getSetpoint());
    	System.out.println("goal: " + goal + ", initial: " + initial + ", setpoint: " + getSetpoint()); //TODO remove debug
    	
    }

    @Override
    protected void execute() {}

    /**
     * @returns whether the current angle is within 2 degrees of desired angle
     */
    @Override
    protected boolean isFinished() {
        if(past && getPIDController().onTarget()) {
        	return true;
        } else {
        	past = getPIDController().onTarget();
        	return false;
        }
    	/*if(Robot.drive.getYaw() > goal)
    	{
    		return true;
    	}
    	return false;*/
    }

    /**
     * Stops the robot
     */
    @Override
    protected void end() {
        Robot.drive.stop();
    }

    /**
     * Calls end()
     */
    @Override
    protected void interrupted() {
        end();
    }
}
