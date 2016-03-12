package com.mvrt.stronghold.subsystems;

import com.mvrt.lib.Clock;
import com.mvrt.lib.Metronome;
import com.mvrt.lib.StateSpaceFlywheelController;
import com.mvrt.stronghold.Constants;
import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.concurrent.TimeUnit;

public class Flywheel extends PIDSubsystem {

  private Encoder encoder;
  private CANTalon motor;
  private double goalspeed;
  private StateSpaceFlywheelController controller = null;
  private Thread stateSpaceThread = null;
  private Metronome metronome =
      Metronome.metronome(20, TimeUnit.MILLISECONDS, Clock.fpgaOrSystem());

  public Flywheel(String name, int motorId, int encoderA, int encoderB, double kP, double kI,
      double kD) {
    super(name, kP, kI, kD);

    encoder = new Encoder(encoderA, encoderB);
    encoder.setDistancePerPulse(Constants.kFlywheelRotationsPerTick);
    // TODO(nij) figure out unit conversions with Ishan. Internal state-space stuff is m/s.

    motor = new CANTalon(motorId);

    this.setOutputRange(-1.0, 1.0);
    this.getPIDController().disable();
  }

  public void setGoal(double goalspeed) {
    this.goalspeed = goalspeed;

    if (controller == null) {
      // TODO(nij) construct the controller here
      System.out.println("Liskij you need to write the initialization line");
    }
    if (stateSpaceThread == null) {
      stateSpaceThread = new Thread(() -> {
          while (true) {
            this.setVoltage(controller.update(goalspeed, encoder.getRate()));
            metronome.pause();
          }
        });
    }
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
    new SetFlywheelSpeed(this, 0);
  }

  protected void setVoltage(double newVoltage) {
    motor.set(newVoltage / DriverStation.getInstance().getBatteryVoltage());
  }
}
