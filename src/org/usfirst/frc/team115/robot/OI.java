package org.usfirst.frc.team115.robot;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.Joystick;
=======

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
>>>>>>> 281f4ef0ec7c1724f76b2edc51b4888498674a83

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
<<<<<<< HEAD
	
	private Joystick joystick;
	
	public OI() {
		joystick = new Joystick(RobotMap.JOYSTICK);
	}
	
    public Joystick getJoystick() {
    	return joystick;
    }
=======
   Joystick joystick;
   
   public OI()
   {
	   joystick = new Joystick(RobotMap.JOYSTICK);
   }
   
   public Joystick getJoystick()
   {
	   return joystick;
   }
>>>>>>> 281f4ef0ec7c1724f76b2edc51b4888498674a83
}

