package com.mvrt.stronghold.subsystems;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.Robot;
import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Flywheel extends PIDSubsystem {

  private Encoder encoder;
  private CANTalon motor;
  private boolean invert = false;

  public Flywheel(String name, int motorId, int encoderA, int encoderB, double kP, double kI,
      double kD, boolean invert) {
    super(name, kP, kI, kD);

    encoder = new Encoder(encoderA, encoderB);
    encoder.setDistancePerPulse(Constants.kFlywheelRotationsPerTick);

    motor = new CANTalon(motorId);
    this.invert = invert;

    motor.setInverted(invert);

    this.setOutputRange(-1.0, 1.0);
    this.setAbsoluteTolerance(3);
  }

  public void setSpeed(double speed) {
    motor.set(speed);
  }

  public double getSpeed() {
    return motor.getSpeed();
  }

  @Override
  protected double returnPIDInput() {
    double velocity = encoder.getRate();
    SmartDashboard.putBoolean(getName() + " On Goal", Math.abs(velocity - this.getSetpoint()) <= 3);
    SmartDashboard.putNumber(getName() + "Velocity", velocity);
    return invert ? -encoder.getRate() : encoder.getRate();
  }

  @Override
  protected void usePIDOutput(double output) {
    if (output < 0 && getSetpoint() > 0) {
      output = 0;
    }
    if (output > 0 && getSetpoint() < 0) {
      output = 0;
    }
    SmartDashboard.putNumber(getName() + "Flywheel", output);
    motor.set(output);
  }

  public boolean isNearTarget() {
    return Math.abs(returnPIDInput() - this.getSetpoint()) <= 0.5;
  }


  @Override
  protected void initDefaultCommand() {

  }
}
