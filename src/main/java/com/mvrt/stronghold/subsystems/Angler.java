package com.mvrt.stronghold.subsystems;

import com.mvrt.stronghold.Constants;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Angler extends Subsystem {

  private CANTalon anglerOne;
  private CANTalon anglerTwo;

  private DigitalInput hallEffectsBottom;
  private DigitalInput hallEffectsBatter;
  private DigitalInput hallEffectsTop;

  private boolean isBraked;
  private AnalogInput encoder;
  DoubleSolenoid brake;

  private double resetVoltage = 0.00;

  /*
   * @author Marcus Plutowski
   */
  public Angler() {
    anglerOne = new CANTalon(Constants.kAnglerTalonOneId);
    anglerTwo = new CANTalon(Constants.kAnglerTalonTwo);

    anglerTwo.setInverted(true);

    brake = new DoubleSolenoid(Constants.kAnglerBrakePortOne, Constants.kAnglerBrakePortTwo);

    hallEffectsBottom = new DigitalInput(Constants.kAnglerHallEffectsBottomLimit);
    hallEffectsBatter = new DigitalInput(Constants.kAnglerHallEffectsBatter);
    hallEffectsTop = new DigitalInput(Constants.kAnglerHallEffectsTopLimit);

    encoder = new AnalogInput(Constants.kAnglerEncoder);

    isBraked = false;
  }

  public void setOutput(double speed) {
    anglerOne.set(speed);
    anglerTwo.set(speed);
  }

  public void brakeOff() {
    brake.set(DoubleSolenoid.Value.kForward);
    isBraked = false;
  }

  public void brakeOn() {
    brake.set(DoubleSolenoid.Value.kReverse);
    isBraked = true;
  }

  public boolean isBraked() {
    return isBraked;
  }

  public void toggleBrake() {
    if (isBraked) {
      brakeOff();
    } else {
      brakeOn();
    }
  }

  public void stop() {
    brakeOn();
    setOutput(0);
  }

  public double getVoltage() {
    return encoder.getVoltage();
  }

  public double getAngle() {
    return (getVoltage() - resetVoltage) * Constants.kAnglerDegreesPerVolt;
  }

  public void initDefaultCommand() {
  }

  public boolean isBottomLimit() {
    return hallEffectsBottom.get();
  }

  public boolean isTopLimit() {
    return hallEffectsTop.get();
  }

  public boolean isBatterLimit() {
    return hallEffectsBatter.get();
  }
}
