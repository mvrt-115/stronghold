package com.mvrt.stronghold;

import com.kauailabs.navx.frc.AHRS;
import com.mvrt.stronghold.commands.*;
import com.mvrt.stronghold.subsystems.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
  SendableChooser autonPicker;

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
    autonPicker = new SendableChooser();
    autonPicker.addObject("Low Bar", new DriveStraightAuton());
    autonPicker.addDefault("Other Defense", new GenericObstacleAuton());
    autonPicker.addDefault("No Auton", new DoNothingAuton());
    SmartDashboard.putData("Auto Chooser", autonPicker);
    //angler.zero();
    CameraServer.getInstance().setQuality(50);
    CameraServer.getInstance().startAutomaticCapture("cam0");
    new Compressor(Constants.kPcmId).start();
  }

  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  public void autonomousInit() {
    autonomousCommand = (Command)(autonPicker.getSelected());
    if (autonomousCommand != null)
      autonomousCommand.start();
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
    SmartDashboard.putBoolean("Top?", angler.isTopLimit());
    SmartDashboard.putBoolean("Bottom?", angler.isBottomLimit());
    SmartDashboard.putNumber("Left Flywheel Speed", leftFlywheel.getEncRate());
    SmartDashboard.putNumber("Right Flywheel Speed", rightFlywheel.getEncRate());
    SmartDashboard.putNumber("Wheel angle ", operatorInterface.getWheel().getX());
    SmartDashboard.putData("Speed:7", new SetFlywheelSingleSpeed(7));
    SmartDashboard.putData("Speed:8", new SetFlywheelSingleSpeed(8));
    SmartDashboard.putData("Speed:9", new SetFlywheelSingleSpeed(9));
    SmartDashboard.putData("Speed:10", new SetFlywheelSingleSpeed(10));
    SmartDashboard.putData("Speed:11", new SetFlywheelSingleSpeed(11));
    SmartDashboard.putData("Speed:12", new SetFlywheelSingleSpeed(12));
    SmartDashboard.putData("Speed:13", new SetFlywheelSingleSpeed(13));
    SmartDashboard.putData("Speed:14", new SetFlywheelSingleSpeed(14));
    SmartDashboard.putData("Speed:15", new SetFlywheelSingleSpeed(15));
    SmartDashboard.putData("Speed:16", new SetFlywheelSingleSpeed(16));
    SmartDashboard.putData("Speed:17", new SetFlywheelSingleSpeed(17));
    SmartDashboard.putData("Speed:18", new SetFlywheelSingleSpeed(18));
    SmartDashboard.putData("Speed:19", new SetFlywheelSingleSpeed(19));
    SmartDashboard.putData("Speed:20", new SetFlywheelSingleSpeed(20));
    SmartDashboard.putData("Speed:21", new SetFlywheelSingleSpeed(21));
    SmartDashboard.putData("SHOOT", new Shoot());
    SmartDashboard.putData("STOP", new StopFlywheels());

    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  public void testPeriodic() {
    LiveWindow.run();
  }
}
