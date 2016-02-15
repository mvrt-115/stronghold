package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Nolan Nyugen and Heather Baker
 */


public class Punch extends Subsystem {

  private DoubleSolenoid punchSolenoid;

  public Punch() {
    punchSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.PUNCH_SOLENOID_A, RobotMap.PUNCH_SOLENOID_B);
  }

  public void punch() {
    punchSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void retract() {
    punchSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  protected void initDefaultCommand() {

  }

}
