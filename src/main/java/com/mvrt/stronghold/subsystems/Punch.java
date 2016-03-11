package com.mvrt.stronghold.subsystems;

import com.mvrt.stronghold.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Punch extends Subsystem {

  private DoubleSolenoid punch;

  public Punch() {
    punch = new DoubleSolenoid(Constants.kPunchSolenoidA, Constants.kPunchSolenoidB);
  }

  public void extend() {
    punch.set(Value.kForward);
  }

  public void retract() {
    punch.set(Value.kReverse);
  }

  public boolean isExtended() {
    return punch.get().equals(Value.kForward);
  }

  @Override
  public void initDefaultCommand() {

  }
}
