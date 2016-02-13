
package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.subsystems.Angler;
import org.usfirst.frc.team115.robot.subsystems.DriveTrain;
import org.usfirst.frc.team115.robot.subsystems.CompressorSystem;
import org.usfirst.frc.team115.robot.subsystems.FlyWheels;
import org.usfirst.frc.team115.robot.subsystems.Punch;
import org.usfirst.frc.team115.robot.commands.AnglerDown;
import org.usfirst.frc.team115.robot.commands.AnglerStop;
import org.usfirst.frc.team115.robot.commands.AnglerUp;
import org.usfirst.frc.team115.robot.commands.WinchArmLift;
import org.usfirst.frc.team115.robot.commands.WinchRaiseRobot;
import org.usfirst.frc.team115.robot.commands.WinchStop;
import org.usfirst.frc.team115.robot.subsystems.Winch;

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
  
	public static DriveTrain drive;
	public static CompressorSystem compressor;
	public static Punch punch;
	public static Winch winch;
	public static Angler angler;
	public static FlyWheels flyWheels;
	public static OI oi;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  public void robotInit() {
    drive = new DriveTrain();
		compressor = new CompressorSystem();
		punch = new Punch();
		winch = new Winch();
		angler = new Angler();
		flyWheels = new FlyWheels();
		oi = new OI();
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
    SmartDashboard.putData("RaiseRobot ", new WinchRaiseRobot());
    SmartDashboard.putData("LiftArm ", new WinchArmLift());
    SmartDashboard.putData("StopWinch ", new WinchStop());
    SmartDashboard.putData("AngleUp ", new AnglerUp());
    SmartDashboard.putData("AngleDown ", new AnglerDown());
    SmartDashboard.putData("AngleStop ", new AnglerStop());
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

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
