package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.commands.DriveArcadeWithJoystick;
import org.usfirst.frc.team115.robot.commands.DriveStraightWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

   Joystick joystick;
   JoystickButton ds, ps;
   
   public OI() {
	   joystick = new Joystick(RobotMap.JOYSTICK);
	   ds= new JoystickButton(joystick, 1);
	   ps = new JoystickButton(joystick, 2);
	   ds.whileHeld(new DriveStraightWithJoystick());
	   ps.whileHeld(new DriveArcadeWithJoystick(true));
   }
   
   public Joystick getJoystick() {
	   return joystick;
   }
}
