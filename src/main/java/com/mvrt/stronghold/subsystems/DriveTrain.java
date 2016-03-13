package com.mvrt.stronghold.subsystems;

import com.mvrt.lib.Clock;
import com.mvrt.lib.DriveInterpreter;
import com.mvrt.lib.DriveSignal;
import com.mvrt.lib.DriveState;
import com.mvrt.lib.Metronome;
import com.mvrt.lib.PidConstants;
import com.mvrt.lib.TurnInPlaceController;
import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import com.mvrt.stronghold.commands.DriveAustinWithJoystick;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.concurrent.TimeUnit;

public class DriveTrain extends Subsystem {

  private CANTalon frontLeft, frontRight, backLeft, backRight;
  private Encoder leftEncoder, rightEncoder;

  private RobotDrive drive;

  private TurnInPlaceController turnController = null;

  private DriveState driveState = new DriveState(0, 0, 0, 0, 0, 0);

  private Thread turnThread = null;

  private Metronome metronome =
      Metronome.metronome(20, TimeUnit.MILLISECONDS, Clock.fpgaOrSystem());

  public DriveTrain() {
    frontLeft = new CANTalon(Constants.kDriveLeftFrontId);
    frontRight = new CANTalon(Constants.kDriveRightFrontId);
    backLeft = new CANTalon(Constants.kDriveLeftRearId);
    backRight = new CANTalon(Constants.kDriveRightRearId);

    backLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
    backLeft.set(frontLeft.getDeviceID());
    backRight.changeControlMode(CANTalon.TalonControlMode.Follower);
    backRight.set(frontRight.getDeviceID());

    frontRight.setInverted(true);

    drive = new RobotDrive(frontLeft, frontRight);

    leftEncoder = new Encoder(Constants.kDriveEncoderLeftA, Constants.kDriveEncoderLeftB);
    rightEncoder = new Encoder(Constants.kDriveEncoderLeftA, Constants.kDriveEncoderRightB);

    leftEncoder.setDistancePerPulse(Constants.kDriveDistancePerTick);
    rightEncoder.setDistancePerPulse(Constants.kDriveDistancePerTick);

    resetEncoders();
  }

  public void setYawGoal(double yaw) {
    turnController = new TurnInPlaceController(getDriveState(), yaw, 100, 20D / 1000D, 200, 100,
        new PidConstants(1, 0, 0), 1 / 100, 0, 3);

    if (turnThread == null) {
      turnThread = new Thread(() -> {
        while (true) {
          update();
          metronome.pause();
        }
      });
    }

    turnThread.interrupt();
  }

  public void update() {
    if (turnController != null && !turnController.isOnTarget()) {
      DriveSignal signal = turnController.update(getDriveState());
      drive.setLeftRightMotorOutputs(signal.leftMotor, signal.rightMotor);
    }
  }

  public void disableController() {
    turnController = null;
    turnThread.interrupt();
    turnThread = null;
  }

  public DriveState getDriveState() {
    driveState.reset(leftEncoder.getDistance(), rightEncoder.getDistance(), leftEncoder.getRate(),
        rightEncoder.getRate(), Robot.navx.getYaw(), Robot.navx.getRate());
    return driveState;
  }

  public boolean turnOnTarget() {
    return turnController.isOnTarget();
  }

  public void drive(double throttle, double rotate, boolean quickturn) {
    DriveSignal signal = DriveInterpreter.austinDrive(throttle, rotate, quickturn);
    drive.setLeftRightMotorOutputs(signal.leftMotor, signal.rightMotor);
  }

  public void stop() {
    DriveSignal signal = DriveInterpreter.stop();
    drive.setLeftRightMotorOutputs(signal.leftMotor, signal.rightMotor);
  }

  public void setLeftRightMotorOutputs(double left, double right) {
    drive.setLeftRightMotorOutputs(left, right);
  }

  public double getDistanceLeft() {
    return leftEncoder.get();
  }

  public double getDistanceRight() {
    return rightEncoder.get();
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  @Override
  protected void initDefaultCommand() {
    this.setDefaultCommand(new DriveAustinWithJoystick());
  }
}
