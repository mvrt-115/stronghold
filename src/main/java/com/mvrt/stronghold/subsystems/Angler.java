package com.mvrt.stronghold.subsystems;

import com.mvrt.stronghold.Constants;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.ConnectionInfo;

public class Angler extends Subsystem {

  private CANTalon anglerOne;
  private CANTalon anglerTwo;

  private DigitalInput hallBottom;
  private DigitalInput hallMiddle;
  private DigitalInput hallTop;

  private AnalogInput encoder;

  private double resetVoltage = 0.00;

  public Angler() {
    anglerOne = new CANTalon(Constants.kAnglerOneId);
    anglerTwo = new CANTalon(Constants.kAnglerTwoId);

    anglerTwo.setInverted(true);

    hallBottom = new DigitalInput(Constants.kAnglerHallBottom);
    hallMiddle = new DigitalInput(Constants.kAnglerHallMiddle);
    hallTop = new DigitalInput(Constants.kAnglerHallTop);

    encoder = new AnalogInput(Constants.kAnglerEncoder);
  }

  public void setOutput(double speed) {
    anglerOne.set(speed);
    anglerTwo.set(speed);
  }

  public void resetEncoders() {
    resetVoltage = encoder.getVoltage();
  }

  public void stop() {
    setOutput(0);
  }

  public double getVoltage() {
    return encoder.getVoltage();
  }

  public double getAngle() {
    return (getVoltage() - resetVoltage) * Constants.kAnglerVoltsPerDegree;
  }

  public void initDefaultCommand() {
  }

  public boolean isBottomHall() {
    return hallBottom.get();
  }

  public boolean isMiddleHall() {
    return hallBottom.get();
  }

  public boolean isTopHall() {
    return hallTop.get();
  }
}
