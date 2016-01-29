package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.commands.AngleShooterDown;
import org.usfirst.frc.team115.robot.commands.AngleShooterStop;
import org.usfirst.frc.team115.robot.commands.AngleShooterUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Joystick joystick;
	private JoystickButton angleUp;
	private JoystickButton angleDown;
	
	public OI() {
		joystick = new Joystick(RobotMap.JOYSTICK);
		
		
		
	}
	
    public Joystick getJoystick(){
    	return joystick;
    }
}

