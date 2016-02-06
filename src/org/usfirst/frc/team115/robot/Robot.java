
package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.subsystems.Angler;
import org.usfirst.frc.team115.robot.subsystems.DriveTrain;
import org.usfirst.frc.team115.robot.subsystems.CompressorSystem;
import org.usfirst.frc.team115.robot.subsystems.ShooterIntake;
import org.usfirst.frc.team115.robot.commands.AngleDown;
import org.usfirst.frc.team115.robot.commands.AngleStop;
import org.usfirst.frc.team115.robot.commands.AngleUp;
import org.usfirst.frc.team115.robot.commands.FireShooter;
import org.usfirst.frc.team115.robot.commands.Intake;
import org.usfirst.frc.team115.robot.commands.LiftArm;
import org.usfirst.frc.team115.robot.commands.RaiseRobot;
import org.usfirst.frc.team115.robot.commands.StopWinch;
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
	public static ShooterIntake shooter;
	public static Winch winch;
	public static Angler angler;
	public static OI oi;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  public void robotInit() {
    drive = new DriveTrain();
		compressor = new CompressorSystem();
		shooter = new ShooterIntake();
		winch = new Winch();
		angler = new Angler();
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
    SmartDashboard.putData("RaiseRobot ", new RaiseRobot());
    SmartDashboard.putData("LiftArm ", new LiftArm());
    SmartDashboard.putData("StopWinch ", new StopWinch());
    SmartDashboard.putData("ShooterIntake ", new Intake());
    SmartDashboard.putData("ShooterFire ", new FireShooter());
    SmartDashboard.putData("AngleUp ", new AngleUp());
    SmartDashboard.putData("AngleDown ", new AngleDown());
    SmartDashboard.putData("AngleStop ", new AngleStop());
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
