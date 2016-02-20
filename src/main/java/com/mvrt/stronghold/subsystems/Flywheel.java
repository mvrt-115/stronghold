package com.mvrt.stronghold.subsystems;

import com.mvrt.lib.CsvWriter;
import com.mvrt.stronghold.Constants;
//import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import com.mvrt.stronghold.commands.SetFlywheelSpeed;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.File;

public class Flywheel extends PIDSubsystem {

  private CANTalon motor;
  private Encoder encoder;

  private CsvWriter writer;
  private double velocity;

  public Flywheel(String name, int motorId, int encoderA, int encoderB, double kP, double kI,
      double kD, boolean invert) {
    super(name, kP, kI, kD);

    motor = new CANTalon(motorId);
    encoder = new Encoder(encoderA, encoderB);

    encoder.setDistancePerPulse(Constants.kFlywheelRotationsPerTick);

    if (invert) {
      motor.setInverted(true);
    }

    try {
      writer = new CsvWriter(new File("/home/lvuser/"+getName()+".csv"), "Encoder Velocity", "Output", "Error");
    } catch (Exception e) {
      e.printStackTrace();
    }

    this.setOutputRange(-1.0, 1.0);
  }

  public void set(double speed) {
    motor.set(speed);
  }

  public CANTalon getMotor() {
    return motor;
  }

  @Override
  protected double returnPIDInput() {
    velocity = encoder.getRate();
    SmartDashboard.putBoolean(getName() + " On Goal", Math.abs(velocity - this.getSetpoint()) <= 3);
    SmartDashboard.putNumber(getName()+ " encoder rate", velocity);
    SmartDashboard.putNumber("Error", (velocity - this.getSetpoint()));
    return velocity;
  }

  @Override
  protected void usePIDOutput(double output) {
    SmartDashboard.putNumber(getName() + " Output", output);
    motor.set(output);
    writer.writeLine(velocity, output, (velocity - getSetpoint()));
  }

  @Override
  protected void initDefaultCommand() {
    new SetFlywheelSpeed(this, 0);
  }
  
  public boolean isNearTarget() {
	if(Math.abs(returnPIDInput() - this.getSetpoint()) <= Constants.kFlywheelOnTargetTolerance){
		return true;
	}
	return false;
  }
}
