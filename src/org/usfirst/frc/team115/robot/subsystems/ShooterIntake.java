package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Code for the intake/shooting motors in the shooter.
 * 
 * @author Ben Cuan & Nolan Nguyen
 * Nolan -- added puncher code
 */
public class ShooterIntake extends Subsystem {
	
	private final int TOP = 1;
	private final int BOTTOM = 2;
	private RobotDrive intake;
    private DoubleSolenoid punchSolenoid;
	
	private CANTalon[] shooterIntake = new CANTalon[2];
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public ShooterIntake() {
		punchSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.PUNCH_SOLENOID_A, RobotMap.PUNCH_SOLENOID_B);
		shooterIntake[TOP] = new CANTalon(RobotMap.SHOOTER_INTAKE_TOP);
		shooterIntake[BOTTOM] = new CANTalon(RobotMap.SHOOTER_INTAKE_BOTTOM);
		
		intake = new RobotDrive(shooterIntake[TOP], shooterIntake[BOTTOM]);
		for(CANTalon shooterIntake: shooterIntake) {
			shooterIntake.setFeedbackDevice(FeedbackDevice.QuadEncoder);
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
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void punch() {//punches, open doubleSolenoid
    	punchSolenoid.set(DoubleSolenoid.Value.kForward);
    	System.out.println("Falcon, PUNCH!");
    }
    public void retract() { //retracts piston, close doubleSolenoid
    	punchSolenoid.set(DoubleSolenoid.Value.kReverse);
    	System.out.println("RETREAT!!!");
    }
    
}

