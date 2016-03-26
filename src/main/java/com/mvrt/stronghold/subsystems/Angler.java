package com.mvrt.stronghold.subsystems;

import com.mvrt.stronghold.Constants;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Angler extends Subsystem {

  private CANTalon anglerOne;
  private CANTalon anglerTwo;

  private boolean isBraked;
  private AnalogInput encoder;
  private DoubleSolenoid brake;

  private DigitalInput upperLimitSwitch;
  private AnalogInput upperLimitHall;

  private DigitalInput lowerLimitSwitch;
  private AnalogInput lowerLimitHall;

  private double resetVoltage = 0.00;

  /*
   * @author Marcus Plutowski
   */
  public Angler() {
    anglerOne = new CANTalon(Constants.kAnglerTalonOneId);
    anglerTwo = new CANTalon(Constants.kAnglerTalonTwoId);

    anglerTwo.setInverted(true);

    //upperLimitSwitch = new DigitalInput(Constants.kUpperLimitSwitchID);
    //upperLimitHall = new AnalogInput(Constants.kUpperLimitHallID);
    //lowerLimitSwitch = new DigitalInput(Constants.kLowerLimitSwitchID);
    //lowerLimitHall = new AnalogInput(Constants.kLowerLimitHallID);

    brake = new DoubleSolenoid(Constants.kPcmId,
            Constants.kAnglerBrakePortOne, Constants.kAnglerBrakePortTwo);

    encoder = new AnalogInput(Constants.kAnglerEncoder);

    isBraked = false;
  }

  public void setOutput(double speed) {
    if (isTopLimit()) {
      speed = Math.min(speed, 0);
    }
    if (isBottomLimit()) {
      speed = Math.max(speed, 0);
    }
    SmartDashboard.putBoolean("Top Limit", isTopLimit());
    SmartDashboard.putBoolean("Bottom Limit", isBottomLimit());
    SmartDashboard.putNumber("Angler output", speed);

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

  public void zero() {
    resetVoltage = getVoltage();
  }

  public double getAverageSpeed() {
    return (anglerOne.getSpeed() + anglerTwo.getSpeed()) / 2;
  }

  public double getVoltage() {
    return encoder.getVoltage();
  }

  public double getAngle() {
    return (getVoltage() - resetVoltage) * Constants.kAnglerDegreesPerVolt;
  }

  public boolean isTopLimit() {
    return false;
    //(upperLimitSwitch.get() || (upperLimitHall.getVoltage() > Constants.kHallVoltageConstant));
  }
  public boolean isBottomLimit() {
    return false;
    //(lowerLimitSwitch.get() || (lowerLimitHall.getVoltage() > Constants.kHallVoltageConstant));
  }

  public void initDefaultCommand() {}
}
