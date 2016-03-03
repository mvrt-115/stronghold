package src.main.java.com.mvrt.stronghold.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import src.main.java.com.mvrt.stronghold.Constants;

public class Climber extends Subsystem {

	CANTalon motor1, motor2;
	
	DoubleSolenoid leftArm, rightArm, brake;
	
	public Climber() {
		motor1 = new CANTalon(Constants.kClimberMotor1);
		motor2 = new CANTalon(Constants.kClimberMotor2);
		
		motor2.changeControlMode(TalonControlMode.Follower);
		motor2.set(motor1.getDeviceID());
				
		leftArm = new DoubleSolenoid(Constants.kPcmId, Constants.kLeftArmSolenoidA, Constants.kLeftArmSolenoidB);
		rightArm = new DoubleSolenoid(Constants.kPcmId, Constants.kRightArmSolenoidA, Constants.kRightArmSolenoidB);
		
		brake = new DoubleSolenoid(Constants.kPcmId, Constants.kClimberBrakeA, Constants.kClimberBrakeB);
	}
	
	public void extendClimber() {
		motor1.disableControl();
		brake.set(Value.kForward);
		leftArm.set(Value.kForward);
		rightArm.set(Value.kForward);
	}
	
	public void winchUp() {
		motor1.enableControl();
		motor1.set(1.0);
	}
	
	public void stop() {
		brake.set(Value.kReverse);
		motor1.set(0);
	}
	
	protected void initDefaultCommand() {
		
	}

}
