package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.commands.Intake;
import org.usfirst.frc.team115.robot.commands.Shoot;
import org.usfirst.frc.team115.robot.commands.Stop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Joystick joystick;
	private JoystickButton shoot;
	private JoystickButton intake;
	
	public OI() {
		joystick = new Joystick(RobotMap.JOYSTICK);
		
		shoot = new JoystickButton(joystick, RobotMap.SHOOT_BUTTON);
		intake = new JoystickButton(joystick, RobotMap.INTAKE_BUTTON);
		
		shoot.whenPressed(new Shoot(0));
		shoot.whenReleased(new Stop());
		
		intake.whenPressed(new Intake());
		intake.whenReleased(new Stop());
		
	}
	
    public Joystick getJoystick(){
    	return joystick;
    }
}

