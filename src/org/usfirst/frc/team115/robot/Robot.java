
package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.commands.Letout;
import org.usfirst.frc.team115.robot.commands.Pullup;
import org.usfirst.frc.team115.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Winch winch;
	public static DoubleSolenoid doublesolenoid;
	public static DoubleSolenoid diskbreak;
	public static Encoder encoder;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		winch = new Winch();
		doublesolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.PORTA, RobotMap.PORTB);
		encoder = new Encoder(RobotMap.ENCODERA, RobotMap.ENCODERB, false, Encoder.EncodingType.k4X);
		diskbreak = new DoubleSolenoid(RobotMap.PCMA, RobotMap.PORTC, RobotMap.PORTD);
        // instantiate the command used for the autonomous period
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	SmartDashboard.putData("Pullup Winch ",new Pullup());
    	SmartDashboard.putData("Letout Winch ", new Letout());
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
