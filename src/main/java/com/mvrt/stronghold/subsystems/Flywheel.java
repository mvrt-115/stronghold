package com.mvrt.stronghold.subsystems;

import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Flywheel extends PIDSubsystem {

  private Encoder encoder;
  private CANTalon motor;

  public Flywheel(String name, int motorId, int encoderA, int encoderB, double kP, double kI,
      double kD) {
    super(name, kP, kI, kD);

    encoder = new Encoder(encoderA, encoderB);
    encoder.setDistancePerPulse(Constants.kFlywheelRotationsPerTick);

    motor = new CANTalon(motorId);

    this.setOutputRange(-1.0, 1.0);
  }

  @Override
  protected double returnPIDInput() {
    double velocity = encoder.getRate();
    SmartDashboard.putBoolean(getName() + " On Goal", Math.abs(velocity - this.getSetpoint()) <= 3);
    return encoder.getRate();
  }

  @Override
  protected void usePIDOutput(double output) {
    motor.set(output);
  }

  @Override
  protected void initDefaultCommand() {
    //new SetFlywheelSpeed(this, 0);
  }
}
