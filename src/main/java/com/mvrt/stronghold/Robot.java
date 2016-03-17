package com.mvrt.stronghold;

import com.kauailabs.navx.frc.AHRS;
import com.mvrt.lib.ConstantsBase;
import com.mvrt.stronghold.commands.AnglerMoveToAngle;
import com.mvrt.stronghold.commands.DisableBrake;
import com.mvrt.stronghold.commands.SpyBotAuton;
import com.mvrt.stronghold.commands.TurnPID;
import com.mvrt.stronghold.subsystems.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
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
            Constants.kFlywheelKd, true);
    rightFlywheel =
        new Flywheel("Right Flywheel", Constants.kFlywheelRightId, Constants.kFlywheelEncoderRightA,
            Constants.kFlywheelEncoderRightB, Constants.kFlywheelKp, Constants.kFlywheelKi,
            Constants.kFlywheelKd, false);
    operatorInterface = new OperatorInterface();
    angler.zero();
    new Compressor(Constants.kPcmId).start();
  }

  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  public void autonomousInit() {
    Scheduler.getInstance().add(new SpyBotAuton());
  }

  /**
   * This function is called periodically during autonomous.
   */
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  public void teleopInit() {
    Scheduler.getInstance().removeAll();

    SmartDashboard.putData("AngleTo30", new AnglerMoveToAngle(-30));
    SmartDashboard.putData("AngleTo15", new AnglerMoveToAngle(-15));
    SmartDashboard.putData("AngleTo45", new AnglerMoveToAngle(-45));
    SmartDashboard.putData("LowBar", new AnglerMoveToAngle(Constants.kAnglerLowBarPreset));
    SmartDashboard.putData("Intake", new AnglerMoveToAngle(0));
    SmartDashboard.putData("Batter", new AnglerMoveToAngle(Constants.kAnglerBatterPreset));
    SmartDashboard.putData("AngleTo100", new AnglerMoveToAngle(-100));
    SmartDashboard.putData("AngleTo110", new AnglerMoveToAngle(-110));
    SmartDashboard.putData("AngleTo111", new AnglerMoveToAngle(-111));
    SmartDashboard.putData("AngleTo112", new AnglerMoveToAngle(-112));
    SmartDashboard.putData("AngleTo105", new AnglerMoveToAngle(-105));
    SmartDashboard.putData("AngleTo107", new AnglerMoveToAngle(-107));
    SmartDashboard.putData("AngleTo108", new AnglerMoveToAngle(-108));
    SmartDashboard.putData("AngleTo100", new AnglerMoveToAngle(-100));
    SmartDashboard.putData("AngleTo102", new AnglerMoveToAngle(-102));

    SmartDashboard.putData("Turn90", new TurnPID(90, true));
    SmartDashboard.putData("Turn45", new TurnPID(45, true));
    SmartDashboard.putData("UnBrake", new DisableBrake());
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
