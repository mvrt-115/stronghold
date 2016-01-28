package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.nav6.frc.IMUAdvanced;

/**
 * Drive Train for the 2016 stronghold competition.
 * 
 * @author Amit Palekar
 *
 */
public class DriveTrain extends Subsystem{
	private CANTalon[] motors = new CANTalon[4];
	private RobotDrive drive;
	private IMUAdvanced navX;
	private final int FRONT_LEFT = 0;
	private final int FRONT_RIGHT = 1;
	private final int BACK_LEFT = 2;
	private final int BACK_RIGHT = 3;
	
	public DriveTrain() {
		motors[FRONT_LEFT] = new CANTalon(RobotMap.FRONT_LEFT_MOTOR);
		motors[FRONT_RIGHT] = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR);
		motors[BACK_LEFT] = new CANTalon(RobotMap.BACK_LEFT_MOTOR);
		motors[BACK_RIGHT] = new CANTalon(RobotMap.BACK_RIGHT_MOTOR);
		
		navX = new IMUAdvanced(new SerialPort(57600, SerialPort.Port.kMXP));
		
		drive = new RobotDrive(motors[FRONT_LEFT], motors[FRONT_RIGHT], motors[BACK_LEFT],
							   motors[BACK_RIGHT]);
		
		for(CANTalon motor : motors) {
			motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		}
		
		resetEncoders();
	}
	
	public void drive(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}
	
	public void drive(Joystick joystick) {
		drive.arcadeDrive(joystick);
	}
	
	public void control(double leftOutput, double rightOutput) {
		drive.setLeftRightMotorOutputs(leftOutput, rightOutput);
	}
	
	public void stop() {
		drive(0, 0);
	}
	
	public void resetEncoders() {
		for(CANTalon m:motors) {
			m.reset();
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub		
	}
	
	/**
     * @return the angle of rotational displacement
     */
    public float getYaw() {
        return (navX.getYaw() + 360)%360;
    }

    /**
     * @return the angle of tilt along the horizontal plane
     */
    public float getPitch() {
        return navX.getPitch();
    }

    /**
     * @return the angle of tilt along the vertical plane
     */
    public float getRoll() {
        return navX.getRoll();
    }

    /**
     * @return the displacement along x axis
     */
    public float getX() {
        return navX.getWorldLinearAccelX();
    }

    /**
     * @return the displacement along x axis
     */
    public float getY() {
        return navX.getWorldLinearAccelY();
    }

    /**
     * @return the displacement along x axis
     */
    public float getZ() {
        return navX.getWorldLinearAccelZ();
    }
}
