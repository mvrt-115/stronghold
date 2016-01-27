package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.commands.Intake;
import org.usfirst.frc.team115.robot.commands.Stop;
import org.usfirst.frc.team115.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private JoystickButton intake, shooter;
    private Joystick joystick;
    
    public OI()
    {
    	joystick = new Joystick(RobotMap.JOYSTICK);
    	shooter = new JoystickButton(joystick, RobotMap.SHOOT_BUTTON);
    	intake = new JoystickButton(joystick, RobotMap.INTAKE_BUTTON);
    	
    	intake.whenPressed(new Intake());
    	intake.whenReleased(new Stop());
    	
    	shooter.whenPressed(new Intake());
    	shooter.whenReleased(new Stop());
    }
}

