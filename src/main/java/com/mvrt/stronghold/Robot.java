package com.mvrt.stronghold;

import com.kauailabs.navx.frc.AHRS;
import com.mvrt.stronghold.commands.DriveStraightAuton;
import com.mvrt.stronghold.subsystems.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
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

  public static OperatorInterface operatorInterface;
  public static AHRS navx;
  public static DriveTrain drive;
  public static Angler angler;
  public static Flywheel leftFlywheel;
  public static Flywheel rightFlywheel;
  public static ActiveIntake intake;
  public static Punch punch;

  Command autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  public void robotInit() {
    drive = new DriveTrain();
    angler = new Angler();
    navx = new AHRS(SPI.Port.kMXP);
    punch = new Punch();
    intake = new ActiveIntake();
    leftFlywheel =
        new Flywheel("Left Flywheel", Constants.kFlywheelLeftId, Constants.kFlywheelEncoderLeftA,
            Constants.kFlywheelEncoderLeftB, Constants.kFlywheelKp, Constants.kFlywheelKi,
            Constants.kFlywheelKd, false);
    rightFlywheel =
        new Flywheel("Right Flywheel", Constants.kFlywheelRightId, Constants.kFlywheelEncoderRightA,
            Constants.kFlywheelEncoderRightB, Constants.kFlywheelKp, Constants.kFlywheelKi,
            Constants.kFlywheelKd, true);
    operatorInterface = new OperatorInterface();
    angler.zero();
    CameraServer.getInstance().setQuality(50);
    CameraServer.getInstance().startAutomaticCapture("cam0");
    new Compressor(Constants.kPcmId).start();
  }

  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  public void autonomousInit() {
    autonomousCommand = new DriveStraightAuton();
    if (autonomousCommand != null) autonomousCommand.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  public void teleopInit() {
    SmartDashboard.putData("Drive Straight", new DriveStraightAuton());
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
    SmartDashboard.putNumber("Angler angle", angler.getAngle());

    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  public void testPeriodic() {
    LiveWindow.run();
  }
}
