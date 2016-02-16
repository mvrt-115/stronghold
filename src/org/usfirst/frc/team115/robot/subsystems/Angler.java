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
 *@author Ben Cuan, Nolan Nguyen, Rithvik Chuppala, and Heather Baker
 */


public class Angler extends Subsystem {
  
  private CANTalon lead, follower;
  
  private double resetVoltage = 1.00;
  
  private AnalogInput[] leftHallEffects = new AnalogInput[3];
  private AnalogInput[] rightHallEffects = new AnalogInput[3];
  
  private AnalogInput encoder;
  
  private static final double HALL_EFFECT_ACTIVE = 3.00; // TODO
  private final static double VOLTAGE_PER_DEGREE = 1.00; // TODO
  
  public static final double PRESET_INTAKE = 0;
  public static final double PRESET_SHOOT_BATTER = 90 * VOLTAGE_PER_DEGREE;
  public static final double PRESET_LOW_BAR = 45 * VOLTAGE_PER_DEGREE;
  public static final double[] presets = {PRESET_INTAKE, PRESET_SHOOT_BATTER, PRESET_LOW_BAR};
  
  public Angler() {
    lead = new CANTalon(RobotMap.ANGLER_MOTOR_LEFT);
    follower = new CANTalon(RobotMap.ANGLER_MOTOR_RIGHT);
    
    follower.changeControlMode(CANTalon.TalonControlMode.Follower);
    follower.set(lead.getDeviceID());
    
    leftHallEffects[Constants.kBottom] = new AnalogInput(RobotMap.LEFT_ANGLER_HALL_BOTTOM);
    leftHallEffects[Constants.kMiddle] = new AnalogInput(RobotMap.LEFT_ANGLER_HALL_MIDDLE);
    leftHallEffects[Constants.kTop] = new AnalogInput(RobotMap.LEFT_ANGLER_HALL_TOP);  
    
    rightHallEffects[Constants.kBottom]= new AnalogInput(RobotMap.RIGHT_ANGLER_HALL_BOTTOM);
    rightHallEffects[Constants.kMiddle] = new AnalogInput(RobotMap.RIGHT_ANGLER_HALL_MIDDLE);
    rightHallEffects[Constants.kBottom] = new AnalogInput(RobotMap.RIGHT_ANGLER_HALL_BOTTOM);
    
    encoder = new AnalogInput(RobotMap.ANGLER_ENCODER); 
    
    resetEncoders();
  }
  
  public void setOutput(double speed) {
	lead.set(speed);
  }
  
  public void stop() {
    setOutput(0);
  }
  
  public void resetEncoders() {
    resetVoltage = (encoder.getVoltage());
  }
  
  public double getAngle() {
    return ((getVoltage() - resetVoltage) / (VOLTAGE_PER_DEGREE));
  }
  
  public double getVoltage(){
    return (encoder.getVoltage());
  }
  
  /** 
  * The hallEffect parameter number is how many hall effects are under the hall effect that is trying to be read.
  * Example: the number for bottom most hall effect is 0. There are no other hall effects underneath it.
  * @param hallEffect
  * @return
  */
  public boolean isHallEffectActive(int hallEffect) {
    return (leftHallEffects[hallEffect].getVoltage() >= HALL_EFFECT_ACTIVE) ||
    		(rightHallEffects[hallEffect].getVoltage()>= HALL_EFFECT_ACTIVE);
  }
  
  public void initDefaultCommand() {
    
  }
    
}
