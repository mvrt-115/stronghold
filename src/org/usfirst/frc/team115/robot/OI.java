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
  JoystickButton driveStraight, drivePrecision;
  
  public OI() {
    joystick = new Joystick(RobotMap.JOYSTICK);
	
    driveStraight= new JoystickButton(joystick, RobotMap.BUTTON_DRIVE_STRAIGHT);
    drivePrecision = new JoystickButton(joystick, RobotMap.BUTTON_DRIVE_PRECISION);
    
    driveStraight.whileHeld(new DriveStraightWithJoystick());
    drivePrecision.whileHeld(new DriveArcadeWithJoystick(true));
  }
   
  public Joystick getJoystick() {
    return joystick;
  }
  
}
