package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.Robot;
import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * 
 * @author Rithvik Chuppala and Heather Baker
 */


public class Winch extends Subsystem {
	
  private CANTalon winch, winchFollower;
  
  private DoubleSolenoid armSolenoid, brakeSolenoid;
  
  public Winch() {
    winch = new CANTalon(RobotMap.WINCH_MOTOR_LEFT);
    winchFollower = new CANTalon(RobotMap.WINCH_MOTOR_RIGHT);
    
    winchFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
    winchFollower.set(winch.getDeviceID());
    
    armSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.ARM_SOLENOID_A, RobotMap.ARM_SOLENOID_B);
    brakeSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.BRAKE_SOLENOID_A, RobotMap.BRAKE_SOLENOID_B);
  }

  public void driveWinch(double move) {
    winch.set(move);
  }
  
  public void brake() {
    brakeSolenoid.set(DoubleSolenoid.Value.kForward);
  }
  
  public void releaseBrake() {
    brakeSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
  
  public void liftArm() {
    armSolenoid.set(DoubleSolenoid.Value.kForward);
  }
  
  public void stop() {
    brakeSolenoid.set(DoubleSolenoid.Value.kForward);
    driveWinch(0);
  }
  
  @Override
  protected void initDefaultCommand() {
    
  }
  
}
