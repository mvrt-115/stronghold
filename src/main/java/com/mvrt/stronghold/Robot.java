package com.mvrt.stronghold;

import com.kauailabs.navx.frc.AHRS;
import com.mvrt.stronghold.subsystems.Angler;
import com.mvrt.stronghold.subsystems.DriveTrain;
import com.mvrt.stronghold.web.WebServer;
import com.mvrt.stronghold.subsystems.Flywheel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

  public static OperatorInterface operatorInterface;
  public static AHRS navx;
  public static DriveTrain drive;
  public static Angler angler;
  public static Flywheel leftFlywheel;
  public static Flywheel rightFlywheel;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  public void robotInit() {
    drive = new DriveTrain();
    angler = new Angler();
    navx = new AHRS(SPI.Port.kMXP);
    leftFlywheel =
        new Flywheel("Left Flywheel", Constants.kFlywheelLeftId, Constants.kFlywheelEncoderLeftA,
            Constants.kFlywheelEncoderLeftB, Constants.kFlywheelKp, Constants.kFlywheelKi,
            Constants.kFlywheelKd);
    rightFlywheel =
        new Flywheel("Right Flywheel", Constants.kFlywheelRightId, Constants.kFlywheelEncoderRightA,
            Constants.kFlywheelEncoderRightB, Constants.kFlywheelKp, Constants.kFlywheelKi,
            Constants.kFlywheelKd);
    operatorInterface = new OperatorInterface();
    WebServer.startServer();
    new Compressor().start();
  }

  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  public void teleopInit() {
  }

  /**
   * This function is called when the disabled button is hit.
   * You can use it to reset subsystems before shutting down.
   */
  public void disabledInit() {

  }

  /**
   * This function is called periodically during operator control.
   */
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  public void testPeriodic() {
    LiveWindow.run();
  }
}
