package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Code for the intake/shooting motors in the shooter.
 * 
 * @author Ben Cuan, Nolan Nguyen, and Heather Baker
 */


public class ShooterIntake extends Subsystem {
  
  private CANTalon[] shooterIntake = new CANTalon[2];
	private final int LEFT = 0;
	private final int RIGHT = 1;
	
	private Encoder encoderLeft;
	private Encoder encoderRight;

	private RobotDrive intake;
  
	private DoubleSolenoid punchSolenoid;
	
	private final double TICKS_PER_INCH = 1.00; //TODO
    
	public ShooterIntake() {
		punchSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.PUNCH_SOLENOID_A, RobotMap.PUNCH_SOLENOID_B);
		shooterIntake[LEFT] = new CANTalon(RobotMap.SHOOTER_INTAKE_LEFT_MOTOR);
		shooterIntake[RIGHT] = new CANTalon(RobotMap.SHOOTER_INTAKE_RIGHT_MOTOR);
		
		encoderLeft = new Encoder(RobotMap.ENCODER_SHOOTER_LEFT_A, RobotMap.ENCODER_SHOOTER_LEFT_B, false, Encoder.EncodingType.k4X);
		encoderRight = new Encoder(RobotMap.ENCODER_SHOOTER_RIGHT_A, RobotMap.ENCODER_SHOOTER_RIGHT_B, false, Encoder.EncodingType.k4X);
		
		
		intake = new RobotDrive(shooterIntake[LEFT], shooterIntake[RIGHT]);
		
		for(CANTalon si: shooterIntake) {
			si.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		}
		
		resetEncoders();
	}
	
	public void drive(double move) {
		intake.arcadeDrive(move, 0);
	}
	
	public void drive(Joystick joystick) {
		intake.arcadeDrive(joystick);
	}
	
	
	public void stop() {
		drive(0);
	}
	
	public void resetEncoders() {
		for(CANTalon m:shooterIntake) {
			m.setPosition(0);
		}
	}
	
	public double getDistance() {
	  return ((encoderLeft.getDistance() + encoderRight.getDistance())/(2 * TICKS_PER_INCH));
	}
	
  public void initDefaultCommand() {
    	
  }
    
  public void punch() {
    punchSolenoid.set(DoubleSolenoid.Value.kForward);
  }
    
  public void retract() {
    punchSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
    
}

