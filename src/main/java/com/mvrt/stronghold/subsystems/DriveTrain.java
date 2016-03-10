package com.mvrt.stronghold.subsystems;

import com.mvrt.lib.DriveInterpreter;
import com.mvrt.lib.DriveSignal;
import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.commands.DriveAustinWithJoystick;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

  private CANTalon frontLeft, frontRight, backLeft, backRight;
  private Encoder leftEncoder, rightEncoder;

  private RobotDrive drive;

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

  public void drive(double throttle, double rotate, boolean quickturn) {
    DriveSignal signal = DriveInterpreter.austinDrive(throttle, rotate, quickturn);
    drive.setLeftRightMotorOutputs(signal.leftMotor, signal.rightMotor);
  }

  public void drive(double driveSpeed, double turnSpeed) {
    DriveSignal signal = DriveInterpreter.arcade(driveSpeed, turnSpeed);
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
