package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Code for the motors that control the angle of the shooter.
 *
 *@author Ben Cuan, Nolan Nguyen, and Heather Baker
 */


public class Angler extends Subsystem {
	
  private CANTalon[] anglers = new CANTalon[2];
	
	private RobotDrive angler;
	
	private double resetVoltage = 1.00;
	
	private AnalogInput[] hallEffects = new AnalogInput[3];
	
	private AnalogInput[] encoders = new AnalogInput[2];
	
	private static final double HALL_EFFECT_ACTIVE = 3.00; // TODO
	private final static double VOLTAGE_PER_DEGREE = 1.00; // TODO

  private static final double PRESET_INTAKE = 0;
  private static final double PRESET_SHOOT_BATTER = 90 * VOLTAGE_PER_DEGREE;
  private static final double PRESET_LOW_BAR = 45 * VOLTAGE_PER_DEGREE;
  public static final double[] presets = {PRESET_INTAKE, PRESET_SHOOT_BATTER, PRESET_LOW_BAR};
    
	public Angler() {
		anglers[Constants.kLeft] = new CANTalon(RobotMap.ANGLER_MOTOR_LEFT);
		anglers[Constants.kRight] = new CANTalon(RobotMap.ANGLER_MOTOR_RIGHT);
		
		angler = new RobotDrive(anglers[Constants.kLeft], anglers[Constants.kRight]);
		
    hallEffects[Constants.kBottom] = new AnalogInput(RobotMap.ANGLER_HALL_BOTTOM);
    hallEffects[Constants.kMiddle] = new AnalogInput(RobotMap.ANGLER_HALL_MIDDLE);
    hallEffects[Constants.kTop] = new AnalogInput(RobotMap.ANGLER_HALL_TOP);
    
    encoders[Constants.kLeft] = new AnalogInput(RobotMap.ANGLER_ENCODERS_LEFT);
    encoders[Constants.kRight] = new AnalogInput(RobotMap.ANGLER_ENCODERS_RIGHT);

		resetEncoders();
	}

	public void setOutput(double speed) {
		angler.arcadeDrive(speed, 0);
	}

	public void stop() {
		setOutput(0);
	}

	public void resetEncoders() {
		resetVoltage = (encoders[Constants.kLeft].getVoltage() + encoders[Constants.kRight].getVoltage())/2;
		}

	public double getAngle() {
	  return ((getVoltage() - resetVoltage) / (VOLTAGE_PER_DEGREE));
	}
	
	public double getVoltage(){
		return ((encoders[Constants.kLeft].getVoltage() * 0.5)+ (encoders[Constants.kRight].getVoltage() * 0.5));
	}
	/** 
	 * The hallEffect parameter number is how many hall effects are under the hall effect that is trying to be read.
	 * Example: the number for bottom most hall effect is 0. There are no other hall effects underneath it.
	 * @param hallEffect
	 * @return
	 */
	public boolean isHallEffectTrue(int hallEffect) {
		if(hallEffects[hallEffect].getVoltage() >= HALL_EFFECT_ACTIVE)
			return true;
		return false;
	}

	public void initDefaultCommand() {}
    
  }

